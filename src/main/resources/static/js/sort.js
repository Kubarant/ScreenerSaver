document.addEventListener("DOMContentLoaded", function(event) {

	let searchInput = document.getElementById("search_menu");
	searchInput.oninput = function() {
		let images = document.getElementsByClassName("elo");
		filterImagesByTitle(images,searchInput.value)
	}

	});

	function filterImagesByTitle(images, title) {
		for(img of images){
			if(textSimilarity(img.title, title) < 0.5){
				img.parentElement.classList.add("image-hiding");
			}
			else{
				img.parentElement.classList.remove("image-hiding");
			}
		}
	}

	function textSimilarity(texta, textb) {
		texta = texta.trim().toLowerCase();
		textb = textb.trim().toLowerCase();
		let common = 0;

		for (let i = 0; i < texta.length; i++) {
			if (textb.length <= i) {
				break;
			}
			else if (texta.charAt(i) == textb.charAt(i)) {
				common++;
			}
		}
		let smaller = Math.min(texta.length, textb.length);
		let jacIndex = common / ((smaller * 2) - common)
		return jacIndex;
	}
