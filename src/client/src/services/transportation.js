import axios from "axios";

const baseURL = "https://passenger-railway-transportati.herokuapp.com/api";

// Get transportation by city name
export const getTransportationByCityName = async cityName => {
	const res = await axios.get(`${baseURL}/transportation/routes/${cityName}`);
	return res.data;
};

// Get all cities
export const getAllCities = async () => {
	const res = await axios.get(`${baseURL}/transportation/stations/locations`);
	return res.data;
};

// Get transportation train seats number
export const getTransportationTrainSeatsNumber = async trainId => {
	const res = await axios.get(`${baseURL}/transportation/routes/${trainId}/train/getSeatsNumber`);
	return res.data;
};
