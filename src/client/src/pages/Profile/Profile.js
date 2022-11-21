import React, { useEffect, useState } from "react";
import { AiOutlineUser } from "react-icons/ai";
import { MdExpandMore } from "react-icons/md";
import { HiOutlineFaceFrown } from "react-icons/hi2";
import {
	Accordion,
	AccordionSummary,
	Typography,
	AccordionDetails,
	TableCell,
	TableHead,
	TableRow,
	TableBody,
	TableContainer,
	Paper,
	Table,
} from "@mui/material";

import styles from "./styles/Profile.module.css";

import { getPurchaseHistory, getTickets, getUserID } from "../../services/user";

function Profile() {
	const userEmail = localStorage.getItem("email");
	const [history, setHistory] = useState([]);
	const [tickets, setTickets] = useState([]);

	useEffect(() => {
		async function fetchData() {
			try {
				const userId = await getUserID(userEmail);

				if (userId) {
					const history = await getPurchaseHistory(userId);
					const tickets = await getTickets(userId);

					setHistory(history);
					setTickets(tickets);
				}
			} catch (err) {
				console.log(err);
			}
		}

		fetchData();
	}, []);

	const renderTableCell = text => <TableCell key={text}>{text}</TableCell>;

	const renderTableHead = params => {
		return (
			<TableHead className={styles.table_header}>
				<TableRow>{params.map(renderTableCell)}</TableRow>
			</TableHead>
		);
	};

	const renderHistoryTableBody = () => {
		return (
			<TableBody>
				{history.map(({ date, ticket, id }) => {
					const { placeNumber, price, transportation } = ticket;
					const { arrivalTime, firstStation, secondStation, train } = transportation;

					const purchaseDate = new Date(date).toLocaleString("uk-UA");
					const time = new Date(arrivalTime).toLocaleString("uk-UA");
					const fStation = firstStation.name;
					const sStation = secondStation.name;
					const trainName = train.name;

					return (
						<TableRow key={id}>
							{[purchaseDate, placeNumber, `${price} uah.`, time, fStation, sStation, trainName].map(renderTableCell)}
						</TableRow>
					);
				})}
			</TableBody>
		);
	};

	const renderTicketsTableBody = () => {
		return (
			<TableBody>
				{tickets.map(({ id, placeNumber, price, transportation }) => {
					const { arrivalTime, firstStation, secondStation, train } = transportation;

					const time = new Date(arrivalTime).toLocaleString("uk-UA");
					const fStation = firstStation.name;
					const sStation = secondStation.name;
					const trainName = train.name;

					return <TableRow key={id}>{[placeNumber, `${price} uah.`, time, fStation, sStation, trainName].map(renderTableCell)}</TableRow>;
				})}
			</TableBody>
		);
	};

	const renderHistory = () => {
		if (!history.length) {
			return (
				<div className={styles.wrapper_typography}>
					<HiOutlineFaceFrown size={64} />
					<p style={{ margin: 0, fontSize: "32px" }}>No Data Found!</p>
				</div>
			);
		}

		return (
			<TableContainer component={Paper}>
				<Table>
					{renderTableHead(["Date", "Place number", "Price (UAH.)", "Arrival time", "Sending station", "Arrival station", "Train number"])}
					{renderHistoryTableBody()}
				</Table>
			</TableContainer>
		);
	};

	const renderTickets = () => {
		if (!tickets.length) {
			return (
				<div className={styles.wrapper_typography}>
					<HiOutlineFaceFrown size={64} />
					<p style={{ margin: 0, fontSize: "32px" }}>No Data Found!</p>
				</div>
			);
		}

		return (
			<TableContainer component={Paper}>
				<Table>
					{renderTableHead(["Place number", "Price (UAH.)", "Arrival time", "Sending station", "Arrival station", "Train number"])}
					{renderTicketsTableBody()}
				</Table>
			</TableContainer>
		);
	};

	return (
		<div className={styles.wrapper}>
			<div className={styles.profile}>
				<AiOutlineUser size={128} />
				<span>{userEmail}</span>
			</div>
			<div>
				<Accordion>
					<AccordionSummary
						sx={{ flexDirection: "row-reverse" }}
						expandIcon={<MdExpandMore size={32} />}
						aria-controls="purchase-history"
						id="purchase-history">
						<Typography component={"span"}>Purchase history</Typography>
					</AccordionSummary>
					<AccordionDetails>
						<Typography component={"span"}>{renderHistory()}</Typography>
					</AccordionDetails>
				</Accordion>
				<Accordion>
					<AccordionSummary sx={{ flexDirection: "row-reverse" }} expandIcon={<MdExpandMore size={32} />} aria-controls="tickets" id="tickets">
						<Typography component={"span"}>Tickets</Typography>
					</AccordionSummary>
					<AccordionDetails>
						<Typography component={"span"}>{renderTickets()}</Typography>
					</AccordionDetails>
				</Accordion>
			</div>
		</div>
	);
}

export default Profile;
