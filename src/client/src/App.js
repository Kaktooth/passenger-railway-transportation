import { Route, Routes } from "react-router-dom";

import Header from "./components/Header/Header";

import Empty from "./pages/Empty/Empty";
import Stations from "./pages/Stations/Stations";
import Transportation from "./pages/Transportation/Transportation";

function App() {
	const routes = (
		<Routes>
			<Route path="*" element={<Empty />} />
			<Route path="/stations" element={<Stations />} />
			<Route path="/transportation" element={<Transportation />} />
		</Routes>
	);

	const getRoutes = routes => {
		const paths = [];

		const walkTree = element => {
			const { children, path } = element.props;
			if (children && children.length > 0) children.forEach(walkTree);
			else if (path && typeof path === "string" && path !== "*" && path !== "/logout") paths.push(element.props.path);
		};

		walkTree(routes);
		return paths;
	};

	return (
		<>
			<Header routes={getRoutes(routes)} />
			{routes}
		</>
	);
}

export default App;
