/*
En este js se realizan las siguientes tareas:
- Maneja la carga de los snipers (fragmentos de código) mediante el menu vertical
- Calcula el alto del div del menu vertical en base al alto del footer
- Carga el menu vertical con efecto acordeón

*/

var maxLenghtString = 18; //si una cadena supera este valor se corta la cadena y se añaden puntos suspensivos

$(document).ready(function () {
	$("#menu").accordion();
	$(".bgsubmenu a").click(function() {
		$(".bgsubmenu li").addClass("active");	
		//$("li").css("background-color", "#000");
		//$(".bgsubmenu li").css("background-color", "red");		
	});	
	$(".menu").css('height', $(document).height() + ($(".footer").height() - 70));
	$("#mnu-history").click(function () {
		$("#main-content").empty();
		$("#main-content").load("history.html");
		collapseSubmenus("#mnu-history");
	});		
	$("#mnu-todaysstatus").click(function () {
		$("#main-content").empty();
		$("#main-content").load("todaysstatus.html");
		collapseSubmenus("#mnu-todaysstatus");
	});		
	$("#mnu-templatesuploaded").click(function () {
		$("#main-content").empty();
		$("#main-content").load("templates_uploaded.html");
		collapseSubmenus("#mnu-templatesuploaded");
	});	
	$("#mnu-sicorresponden").click(function () {
		$("#main-content").empty();
		$("#main-content").load("sicorresponden.html");
		collapseSubmenus("#mnu-sicorresponden");
	});
	$("#mnu-pc-siprodernomb").click(function () {
		$("#main-content").empty();
		$("#main-content").load("productcateg_siprodernomb.html");
	});
	$("#mnu-pc-siprodclase").click(function () {
		$("#main-content").empty();
		$("#main-content").load("productcateg_siprodclase.html");
	});	
	$("#mnu-cvg-sicvg").click(function () {
		$("#main-content").empty();
		$("#main-content").load("cvg_sicvg.html");
	});
	$("#mnu-cvg-sioperaci").click(function () {
		$("#main-content").empty();
		$("#main-content").load("cvg_sicvgoperaci.html");
	});
	$("#mnu-cvg-sicontab").click(function () {
		$("#main-content").empty();
		$("#main-content").load("cvg_sicvgcontab.html");
	});	
	$("#mnu-siempresaprod").click(function () {
		$("#main-content").empty();
		$("#main-content").load("siempresaprod.html");
	});
	$("#mnu-siempresasubp").click(function () {
		$("#main-content").empty();
		$("#main-content").load("siempresasubp.html");
	});
});

