function makeSplashScreen(imagepath, image, width,height) {
    var splash = document.createElement("div");
    splash.style = "position: fixed; width: 100%;height: 100%; margin: 0; background-color: rgba(25, 25, 25, 0.45);z-index: 995;display:flex; justify-content:center; align-items:center;";
    splash.id = "splash";
    let prog =makeProgressiveImg(image, width,height);
    splash.appendChild(prog);
    var splashimg = document.createElement("img");
    splashimg.style = "max-height:90vh;max-width:95vw;z-index:5;";
    splashimg.src = imagepath;
    splashimg.onload=function(){
    	splash.removeChild(prog);
    	splash.appendChild(splashimg);
    };
    return splash;
}

function makeProgressiveImg(image, width, height) {
    let img = new Image();
    img.src =image.src;
    console.log(width + "  " + height);
    img.style = "filter: blur(45px);max-height:90vh;max-width:95vw;z-index:3;";
    img.style.width = width-30 + "px";
    img.style.height = height-30 + "px";

    return img;
}

function applyGalleryImages() {
    let images = document.getElementsByTagName("img");

    for (var i = 0, len = images.length; i < len; i++) {
        let img = images[i];
        addSplashImageListener(img);
 
    }
}

function galleryAction(img) {
	
    let org = img.getAttribute("data-original");
    let originw = img.dataset.a;
    let originh = img.getAttribute("data-originh");

    let left = ((screen.width - originw) / screen.width) * 50;
    let top = ((screen.height - originh) / screen.height) * 50;
    let splash = makeSplashScreen(org, img, originw, originh);
    //let splash = makeProgressiveScreen(img, originw, originh);
    document.getElementsByTagName("body")[0].appendChild(splash);

    splash.addEventListener("click", function() {
        splash.parentElement.removeChild(splash);
    });

}



function addSplashImageListener(img) {
    img.addEventListener("click", function(){galleryAction(img)});

}

function copy(){
	var clipboard = new ClipboardJS('#image-link-button');
	let toast = document.querySelector('#success-info');

	clipboard.on('success', function(e) {	    
		let data = { message: "Skopiowano adres zdjęcia", timeout: 5000 };
        toast.MaterialSnackbar.showSnackbar(data);

	    e.clearSelection();
	});

	clipboard.on('error', function(e) {
		let data = { message: "Nie udało się skopiować adresu zdjęcia", timeout: 4200 };
        toast.MaterialSnackbar.showSnackbar(data);

		
			});
}


document.addEventListener("DOMContentLoaded", function(event) {
    applyGalleryImages();
    copy();
    

});