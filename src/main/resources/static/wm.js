window.wm = {
	markers: [],
	canvas: null,
	map: null,
	info: null,
	current: null,
	url: "http://localhost:8080/",
	path: "workmate/workplaces/"
};



function getCoordinates(lat, lon, callback) {
	var url = wm.url + wm.path + lon + "/" + lat + "/"
	console.log(url);
	get(url, callback);
}


function get(url, callback) {
	var xhr = new XMLHttpRequest();	
	xhr.open('GET', url, true);
	xhr.send();
	xhr.onreadystatechange = function(p) {
		if (xhr.readyState == 4 && xhr.status == 200) {
			if (xhr.responseText) {
				callback(p);
			}
		}
	};
}


function initInfo() {
	wm.info = new google.maps.InfoWindow();
}

function initCanvas() {
  var mapcanvas = document.createElement('div');
  mapcanvas.id = 'mapcanvas';
  mapcanvas.style.height = '400px';
  mapcanvas.style.width = '560px';
  document.querySelector('article').appendChild(mapcanvas);
  wm.canvas = mapcanvas;
  return mapcanvas;
}

function addMarker(lat, lon) {
	var position = new google.maps.LatLng(lat, lon);
	var marker = new google.maps.Marker({
		position: position, 
		map: wm.map
	});

	wm.markers.push(marker);

	google.maps.event.addListener(marker, 'click', function(p) {
		wm.info.setContent("Profile info");
		wm.info.open(map, marker);
	});

	return marker;
}

function initMap(initialPosition) {
	initialPosition = new google.maps.LatLng(52.387916999999995, 4.6336751);
	var opts = {
		zoom: 15,
		center: initialPosition,
		mapTypeControl: false,
		navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL},
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};

	var map = new google.maps.Map(wm.canvas, opts);
	wm.map = map;
	return map;
}

function addMates() {
	if (wm.current) {
		getCoordinates(wm.current.coords.latitude, wm.current.coords.longitude, function(p) {
			console.log(p.srcElement.responseText);
		});
	}
}

function addCurrentLocation() {
  addMarker(wm.current.coords.latitude, wm.current.coords.longitude);
}




