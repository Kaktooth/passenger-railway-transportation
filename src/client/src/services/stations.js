import axios from "axios";

const baseURL = "https://passenger-railway-transportati.herokuapp.com/api";

// Get all stations
export const getAllStations = async () => {
	const res = await axios.get(`${baseURL}/transportation/stations`);
	return res.data;
};
