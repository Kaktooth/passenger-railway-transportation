import axios from "axios";

const baseURL = "https://passenger-railway-transportati.herokuapp.com/api";

// Get transportation by city name
export const getTransportationByCityName = async cityName => {
	const res = await axios.get(`${baseURL}/transportation/routes/${cityName}`);
	return res.data;
};
