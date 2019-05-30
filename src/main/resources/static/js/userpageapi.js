let username="";
let images = [];
let components = [];
document.addEventListener("DOMContentLoaded", function(event) {
    axios.get('/userinfo').then(function(response) {
            username = response.data.username;

            let usernameHeader = document.createElement("h4");
            usernameHeader.id = "username";
            usernameHeader.innerText = username;

            document.getElementById("usernameContainer").appendChild(n);
            let images = response.data.images;

            for (var i = 0; i < images.length; i++) {

                let img = images[i];
                let imageComp = {
                    id: i,
                    title: img.title,
                    token: img.token,
                    createDate: img.createDate,
                    thumbWidth: img.thumbWidth *1.1,
                    thumbHeight: img.thumbHeight *1.1,
                    thumbSrc: "http://127.0.0.1:8080/images/thumb/" + img.title,
                    originalHeight: img.height,
                    originalWidth: img.width,
                    originalSrc: "http://127.0.0.1:8080/images/" + img.title
                };


                 components.push(imageComp)
                    // addSplashImageListener(imgel);
                    // let imggrup = document.getElementById("imagegroup");
            }
            Vue.component('image-com', {
                template: '<div class="img-container">' +
                    '<img onclick="galleryAction(this)" v-bind:src="imageComp.thumbSrc"  v-bind:data-a="imageComp.originalWidth" ' +
                    'v-bind:data-originh="imageComp.originalHeight"v-bind:title="imageComp.title" v-bind:token="imageComp.token" class="elo" v-bind:data-original="imageComp.originalSrc" v-bind:width="imageComp.thumbWidth" v-bind:height="imageComp.thumbHeight">' +
                    '<div class="img-options">{{imageComp.title}}</div>' +
                    '   <div class="image-buttons"> <button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect image-button" id="image-link-button" v-bind:data-clipboard-text="imageComp.originalSrc">' +
                    '<i class="material-icons">link</i>  </button>' +
                    '  <button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect image-button" id="image-link-button" v-bind:target="imageComp.title" onClick="bdelete(this)"> <i class="material-icons">delete</i> </button>   </div>  </div>'
                ,
                props: ['imageComp']
            });


            new Vue({
                el: "#imagegroup",
                data: {
                    components: components
                }
            });
        })
        .catch(function(error) {
            console.log(error);
        });



/*  Delete Dialog */
    let dialog = document.getElementById('delete-image-dialog');
    if (! dialog.showModal) {
    	dialogPolyfill.registerDialog(dialog);
    }
    dialog.querySelector('.close').addEventListener('click', function() {
    	dialog.close();
    });
    dialog.querySelector('.delete').addEventListener('click', function() {
    	deleteImage(dialog.targetTitle);
    	dialog.close();
    });
});



function deleteImage(title) {
    axios({
        method: 'post',
        url: '/images/delete',
        data: {
            title: title
        }
    });
}


    function bdelete(button) {
    	let dialog = document.getElementById('delete-image-dialog');
    	let targetTitle = button.getAttribute("target");
    	dialog.targetTitle = targetTitle;
    	let imgTitle =document.getElementById("deleted-image-label");
    	imgTitle.innerText  = targetTitle;
    	dialog.showModal();
    }
    
