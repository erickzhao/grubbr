<!DOCTYPE html>
<html>
	<head> 
		<meta charset="UTF-8">
		<title> Food Truck Manager </title>
		<style>
			.error {color:#FF0000;}
		</style>
	</head>
	<body>
		<?php 
			require_once "model/MenuItem.php";
			require_once "model/FoodTruckManager.php";
			require_once "persistence/PersistenceFoodTruck.php";
			session_start();
		?>
		<h1> Add Item </h1>
		<form action = "additem.php" method="post">
			<fieldset>
			<br>
			<p>Item Name: <input type="text" name="newitem_name" placeholder="Enter Name"/>
			<p>Item Price: <input type="text" name="newitem_price" placeholder="Enter Price"/>
			<span class="error">
			<?php
			if (isset($_SESSION['errorItem']) && !empty($_SESSION['errorItem'])){
				echo " * " . $_SESSION["errorItem"];
			}
			?>
			</span></p>	
			<p><input type="submit" value="Add Item"/></p>
			<br>
			</fieldset>
		</form>
		<span>
			<?php 
			$ftm = FoodTruckManager::getInstance();
			for($i=0;$i<sizeof($ftm->getMenuItems());$i++){
				echo "Menu";
				echo $ftm->getMenuItem_index($i)->getName()+" : "+getMenuItem_index($i)->getPrice()+" $ ";
			}
			?>
		</span>
		
	</body>
</html>
