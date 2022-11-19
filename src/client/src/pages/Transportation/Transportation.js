import * as React from "react";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from "@mui/material";

import { getTransportationByCityName } from "../../services/transportation";

import styles from "./styles/Transportation.module.css";

function Transportation() {
	const [transportation, setTransportation] = React.useState([]);

	React.useEffect(() => {
		async function fetchData() {
			try {
				const res = await getTransportationByCityName("Cherkasy");
				setTransportation(res);
			} catch (err) {
				console.log(err);
			}
		}

		fetchData();
	}, []);

	const renderTableCell = text => <TableCell key={text}>{text}</TableCell>;

	const renderTableHead = () => {
		return (
			<TableHead>
				<TableRow>{["Arrival time", "Sending station", "Arrival station", "Train number"].map(renderTableCell)}</TableRow>
			</TableHead>
		);
	};

	const renderTableBody = () => {
		return (
			<TableBody>
				{transportation.map(({ train, arrivalTime, firstStation, secondStation }) => {
					const time = new Date(arrivalTime).toLocaleString("uk-UA");
					const fStation = firstStation.name;
					const sStation = secondStation.name;
					const trainName = train.name;

					return <TableRow key={train.id + arrivalTime}>{[time, fStation, sStation, trainName].map(renderTableCell)}</TableRow>;
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
