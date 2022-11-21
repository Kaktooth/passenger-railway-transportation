import axios from "axios";

const baseURL = "https://passenger-railway-transportati.herokuapp.com/api";

// Get user ID
export const getUserID = async userEmail => {
	const res = await axios.get(`${baseURL}/user/email/${userEmail}`);
	return res.data.id;
};

// Get purchase history
export const getPurchaseHistory = async userID => {
	const res = await axios.get(`${baseURL}/user/${userID}/purchase-history`);
	return res.data;
};

// Get tickets
export const getTickets = async userID => {
	const res = await axios.get(`${baseURL}/user/${userID}/tickets`);
	return res.data;
};
