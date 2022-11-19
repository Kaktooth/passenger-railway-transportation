import { Route, Routes } from "react-router-dom";

import "./App.css";

import Sidebar from "./components/Sidebar/Sidebar";
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
			else if (path && typeof path === "string" && path !== "*") paths.push(element.props.path);
		};

		walkTree(routes);
		return paths;
	};

	return (
		<div className="app">
			<div className="sidebar">
				<Sidebar routes={getRoutes(routes)} />
			</div>
			<div className="content">{routes}</div>
		</div>
	);
}

export default App;
