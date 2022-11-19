import axios from "axios";

const baseURL = "https://passenger-railway-transportati.herokuapp.com";

// Get all stations
export const getAllTickets = async () => {
	return await axios.get(`${baseURL}/tickets`).then(function (response) {
		// handle success
		console.log(response);
	});
};
