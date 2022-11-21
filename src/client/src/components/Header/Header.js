import React, { useState } from "react";
import { SiTrainerroad } from "react-icons/si";
import { GrLogout } from "react-icons/gr";
import { AppBar, Box, Toolbar, Button, Menu, MenuItem } from "@mui/material";
import { NavLink } from "react-router-dom";

import styles from "./styles/Header.module.css";

function Header({ routes }) {
	const [isOpenMenu, setIsOpenMenu] = useState(false);
	const [element, setElement] = useState(null);

	const handleClick = event => {
		setIsOpenMenu(true);
		setElement(event.currentTarget);
	};
	const handleClose = () => setIsOpenMenu(false);

	const renderButton = (text, link, key) => (
		<NavLink className={({ isActive }) => (isActive ? styles.activeLink : styles.link)} to={link} key={key}>
			<Button variant="text" className={styles.button}>
				{text}
			</Button>
		</NavLink>
	);

	return (
		<Box>
			<AppBar style={{ background: "transparent" }} position="static">
				<Toolbar className={styles.toolbar}>
					<div>
						<SiTrainerroad color="#0026ff" size={36} />
					</div>
					<div>
						{routes.map((r, i) => {
							const route = r.replace("/", "");
							return renderButton(route, route, i);
						})}
					</div>
					<div>
						<Button onClick={handleClick} className={styles.welcomeText}>
							<span>Hi, {localStorage.getItem("email")}</span>
						</Button>
						<Menu anchorEl={element} open={isOpenMenu} onClose={handleClose}>
							<MenuItem onClick={handleClose}>My account</MenuItem>
							<NavLink className={styles.activeLink} to={"/login"}>
								<MenuItem sx={{ color: "#000" }} onClick={handleClose}>
									Logout
								</MenuItem>
							</NavLink>
						</Menu>
					</div>
				</Toolbar>
			</AppBar>
		</Box>
	);
}

export default Header;
