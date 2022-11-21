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

// Get all train seats number
export const getAllTrainSeatsNumber = async trainId => {
	const res = await axios.get(`${baseURL}/transportation/routes/${trainId}/train/getSeatsNumber`);
	return res.data;
};

// Get available train seats number
export const getAvailableTrainSeatsNumber = async trainId => {
	const res = await axios.get(`${baseURL}/transportation/routes/${trainId}/train/getAvailableSeats`);
	return res.data;
};
