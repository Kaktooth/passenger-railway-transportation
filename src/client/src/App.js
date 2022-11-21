import { useEffect, useState } from "react";
import { Route, Routes, useNavigate } from "react-router-dom";

import Header from "./components/Header/Header";
import Notify from "./components/Notify/Notify";

import Authorization from "./pages/Authorization/Authorization";
import Empty from "./pages/Empty/Empty";
import Profile from "./pages/Profile/Profile";
import Stations from "./pages/Stations/Stations";
import Transportation from "./pages/Transportation/Transportation";

function App() {
	const [isShowHeader, setShowHeader] = useState();
	const navigate = useNavigate();

	useEffect(() => {
		if (localStorage.getItem("authorized") === "0" || !localStorage.getItem("email")) {
			navigate("/login");
			setShowHeader(false);
		} else setShowHeader(true);
	}, [navigate]);

	const routes = (
		<Routes>
			<Route path="*" element={<Empty />} />
			<Route path="/stations" element={<Stations />} />
			<Route path="/transportation" element={<Transportation />} />
			<Route path="/login" element={<Authorization />} />
			<Route path="/profile" element={<Profile />} />
			<Route path="/notify" element={<Notify />} />
		</Routes>
	);

	const getRoutes = routes => {
		const paths = [];

		const walkTree = element => {
			const { children, path } = element.props;
			if (children && children.length > 0) children.forEach(walkTree);
			else if (path && typeof path === "string" && path !== "*" && path !== "/login" && path !== "/profile") paths.push(element.props.path);
		};

		walkTree(routes);
		return paths;
	};

	return (
		<>
			{isShowHeader && <Header routes={getRoutes(routes)} />}
			{routes}
		</>
	);
}

export default App;
