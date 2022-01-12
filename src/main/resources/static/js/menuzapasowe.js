let galleryImages = document.querySelectorAll(".img-thumbnail");
let getLatestOpenedImg;
let galleryImages2;
let windowWidth = window.innerWidth;
let fullImage;

galleryImages.forEach(function(image){
    image.onclick= function() {
        galleryImagesId = $(this).attr("name");
        galleryImages2 = document.querySelectorAll("[name=" + CSS.escape(galleryImagesId) + "]");

        galleryImages2.forEach(function(image, index){
        image.onclick= function() {
        getLatestOpenedImg = index;
        fullImage = $(galleryImages2[getLatestOpenedImg]).attr('src');
    
            let container = document.body;
            let newImgWindow = document.createElement("div");
            container.appendChild(newImgWindow);
            newImgWindow.setAttribute("class", "image-window");
            newImgWindow.setAttribute("onclick", "closeImg()");

            let newImg = document.createElement("img");
            newImgWindow.appendChild(newImg);
            newImg.setAttribute("src", fullImage);
            newImg.setAttribute("id", "current-img");

            newImg.onload = function(){
            let imgWidth = this.width;
            let calcImgToEdge = ((windowWidth - imgWidth) / 2) -80;
            let newPrevBtn = document.createElement("a");
            let btnPrevText = document.createTextNode("Prev");
            newPrevBtn.appendChild(btnPrevText);
            container.appendChild(newPrevBtn);
            newPrevBtn.setAttribute("class", "img-btn-prev");
            newPrevBtn.setAttribute("onclick", "changeImg(0)");
            newPrevBtn.style.cssText = "left: " + calcImgToEdge + "px;";

            let newNextBtn = document.createElement("a");
            let btnNextText = document.createTextNode("Next");
            newNextBtn.appendChild(btnNextText);
            container.appendChild(newNextBtn);
            newNextBtn.setAttribute("class", "img-btn-next");
            newNextBtn.setAttribute("onclick", "changeImg(1)");
            newNextBtn.style.cssText = "right: " + calcImgToEdge + "px;";
            }
    }
});
    }
});

function closeImg() {
    document.querySelector(".image-window").remove();
    document.querySelector(".img-btn-prev").remove();
    document.querySelector(".img-btn-next").remove();
}

function changeImg(changeDir) {
    document.querySelector("#current-img").remove();
    let getImgWindow = document.querySelector(".image-window");
    let newImg = document.createElement("img");
    getImgWindow.appendChild(newImg);

    let calcNewImg;
    if(changeDir === 1) {
        calcNewImg = getLatestOpenedImg + 1;
        if(calcNewImg > galleryImages2.length-1) {
            calcNewImg = 0;
        }
    }
    else if(changeDir === 0) {
        calcNewImg = getLatestOpenedImg -1;
        if(calcNewImg<0) {
            calcNewImg = galleryImages2.length-1;
        }
    }

    getLatestOpenedImg = calcNewImg;
    fullImage = $(galleryImages2[getLatestOpenedImg]).attr('src');

    newImg.setAttribute("src", fullImage);
    newImg.setAttribute("id", "current-img");

    
    newImg.onload = function() {
        let imgWidth = this.width;
        let calcImgToEdge = ((windowWidth - imgWidth) / 2) -80;
        let nextBtn = document.querySelector(".img-btn-next");
        nextBtn.style.cssText = "right: " + calcImgToEdge + "px;";
        let prevBtn = document.querySelector(".img-btn-prev");
        prevBtn.style.cssText = "left: " + calcImgToEdge + "px;";
    }
}


