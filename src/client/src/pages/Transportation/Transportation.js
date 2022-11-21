import React, { useEffect, useState } from "react";
import { HiOutlineFaceFrown } from "react-icons/hi2";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, FormControl, InputLabel, Select, MenuItem } from "@mui/material";

import { getAllCities, getTransportationByCityName, getTransportationTrainSeatsNumber } from "../../services/transportation";

import styles from "./styles/Transportation.module.css";

function Transportation() {
	const [city, setCity] = useState();
	const [cities, setCities] = useState([]);

	const [transportation, setTransportation] = useState([]);
	const [trainSeatsNumbers, setTrainSeatsNumbers] = useState([]);

	useEffect(() => {
		async function getCities() {
			try {
				const cities = await getAllCities();
				setCities(cities);
			} catch (err) {
				console.log(err);
			}
		}

		getCities();
	}, []);

	useEffect(() => {
		async function getTransportation() {
			try {
				const seatsNumbers = [];

				if (city) {
					const transportation = await getTransportationByCityName(city);

					if (transportation) {
						transportation.forEach(async i => {
							const res = await getTransportationTrainSeatsNumber(i.id);
							seatsNumbers.push(res);
						});
					}

					setTransportation(transportation);
				}

				setTrainSeatsNumbers(seatsNumbers);
			} catch (err) {
				console.log(err);
			}
		}

		getTransportation();
	}, [city]);

	const renderDropdown = () => {
		return (
			<FormControl sx={{ width: 300, mb: "20px" }}>
				<InputLabel id="cityLabel">City</InputLabel>
				<Select labelId="cityLabel" id="city" defaultValue={""} value={city || ""} label="City" onChange={event => setCity(event.target.value)}>
					{cities
						?.sort((a, b) => a - b)
						.map(city => {
							return (
								<MenuItem key={city} value={city || ""}>
									{city}
								</MenuItem>
							);
						})}
				</Select>
			</FormControl>
		);
	};

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
				{transportation.length > 0 ? (
					transportation.map(({ train, arrivalTime, firstStation, secondStation }, index) => {
						const time = new Date(arrivalTime).toLocaleString("uk-UA");
						const fStation = firstStation.name;
						const sStation = secondStation.name;
						const trainName = train.name;

						return (
							<TableRow key={train.id + arrivalTime}>
								{[time, fStation, sStation, trainName, trainSeatsNumbers[index] || 0].map(renderTableCell)}
							</TableRow>
						);
					})
				) : (
					<TableRow>
						<TableCell colSpan={5} sx={{ padding: "40px", textAlign: "center" }}>
							<HiOutlineFaceFrown size={64} />
							<p style={{ margin: 0, fontSize: "32px" }}>No Data Found!</p>
						</TableCell>
					</TableRow>
				)}
			</TableBody>
		);
	};

	return (
		<div className={styles.wrapper}>
			{renderDropdown()}
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
