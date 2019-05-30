var kaps;
let hidden = true;

function sendFile(f) {
    let prog = document.getElementById("sending-prog");
    prog.style.display = "block";
    let toast = document.querySelector('#success-info');
    let title = window.prompt("Podaj tytul obrazu lub zostanie wykorzystana nazwa pliku");

    let data = new FormData();
    data.set("file", f);
    data.set("title", title);

    let xhr = new XMLHttpRequest();
    xhr.open("post", "/upload");
    xhr.onerror = function() {
        let data = { message: "Nie udało sie wysłać zdjęcia", timeout: 15000 };
        toast.MaterialSnackbar.showSnackbar(data);
    }
    xhr.onload = function() {
        let prog = document.getElementById("sending-prog");
        prog.style.display = "none";
        if (xhr.status == 200) {
            let data = {
                message: "Pomyślnie wysłano zdjęcie",
                timeout: 10000
            };
            toast.MaterialSnackbar.showSnackbar(data);
        } else {
            xhr.onerror();
        }

    };
    xhr.send(data);
}

function drop_handler(ev) {
    console.log("Drop");
    ev.preventDefault();
    var dt = ev.dataTransfer;
    if (dt.items) {
        if (dt.items[0].kind == "file") {
            var f = dt.items[0].getAsFile();
            kaps = f;
            sendFile(f);
            console.log(" file name = " + f.name);
        }

    } else {
        for (var i = 0; i < dt.files.length; i++) {
            console.log("... file[" + i + "].name = " + dt.files[i].name);
        }
    }
}

function dragend_handler(ev) {
    console.log("dragEnd");
    var dt = ev.dataTransfer;
    if (dt.items) {
        for (var i = 0; i < dt.items.length; i++) {
            dt.items.remove(i);
        }
    } else {
        ev.dataTransfer.clearData();
    }
}

function dragover_handler(ev) {
    console.log("dragOver");
    ev.preventDefault();
}

function fileExplore() {
    let input = document.getElementById("hidden-file");
    input.click();
}

function addFile(file) {
    let input = document.getElementById("hidden-file");
    if (input.files[0].size > 0) {
        sendFile(input.files[0]);
    }

}

function showDropZone() {
    let dropzone = document.getElementById("drop-zone");
    dropzone.style.display = hidden ? "grid" : "none";
    hidden = !hidden;
}