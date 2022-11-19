import * as React from "react";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from "@mui/material";

import styles from "./styles/Stations.module.css";

import { getAllStations } from "../../services/stations";

function Stations() {
	const [stations, setStations] = React.useState([]);

	React.useEffect(() => {
		async function fetchData() {
			try {
				const res = await getAllStations();
				setStations(res);
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
				<TableRow>{["Name", "Location"].map(renderTableCell)}</TableRow>
			</TableHead>
		);
	};

	const renderTableBody = () => {
		return (
			<TableBody>
				{stations.map(({ id, name, location }) => {
					return <TableRow key={id}>{[name, location].map(renderTableCell)}</TableRow>;
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

export default Stations;
