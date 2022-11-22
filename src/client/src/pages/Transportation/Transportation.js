import * as React from "react";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from "@mui/material";

import { getTransportationByCityName, getTransportationTrainSeatsNumber } from "../../services/transportation";

import styles from "./styles/Transportation.module.css";

function Transportation() {
	const [transportation, setTransportation] = React.useState([]);
	const [trainSeatsNumbers, setTrainSeatsNumbers] = React.useState([]);

	React.useEffect(() => {
		async function fetchData() {
			try {
				const seatsNumbers = [];

				const transportation = await getTransportationByCityName("Cherkasy");
				if (transportation) {
					transportation.forEach(async i => {
						const res = await getTransportationTrainSeatsNumber(i.id);
						seatsNumbers.push(res);
					});
				}

				setTransportation(transportation);
				setTrainSeatsNumbers(seatsNumbers);
			} catch (err) {
				console.log(err);
			}
		}

		fetchData();
	}, []);

	const renderTableCell = text => <TableCell key={text}>{text}</TableCell>;

	const renderTableHead = () => {
		return (
			<TableHead className={styles.table_header}>
				<TableRow>{["Arrival time", "Sending station", "Arrival station", "Train number", "Seats number"].map(renderTableCell)}</TableRow>
			</TableHead>
		);
	};

	const renderTableBody = () => {
		return (
			<TableBody>
				{transportation.map(({ train, arrivalTime, firstStation, secondStation }, index) => {
					const time = new Date(arrivalTime).toLocaleString("uk-UA");
					const fStation = firstStation.name;
					const sStation = secondStation.name;
					const trainName = train.name;

					return (
						<TableRow key={train.id + arrivalTime}>
							{[time, fStation, sStation, trainName, trainSeatsNumbers[index] || 0].map(renderTableCell)}
						</TableRow>
					);
				})}
			</TableBody>
		);
	};

	return (
		<div className={styles.wrapper}>
			<TableContainer component={Paper}>
				<Table>
					{renderTableHead()}
					{renderTableBody()}
				</Table>
			</TableContainer>
		</div>
	);
}

export default Transportation;
