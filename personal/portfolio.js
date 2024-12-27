
//slide chuột//
window.onscroll = function() { 
  if (window.innerWidth > 740) {
      scrollFunction(); 
  }
};

window.onresize = function() {
  if (window.innerWidth <= 739) {
    resetHeaderStyles();
  }
}

function scrollFunction() {
  if (document.body.scrollTop > 500 || document.documentElement.scrollTop > 500) {
      document.getElementById("header").style.height = "5rem";
      document.getElementById("logo-personal").style.marginLeft = "6vw";
      document.querySelector(".menu").style.marginRight = "3vw"; 
      const listItems = document.querySelectorAll("li");
      listItems.forEach(function(item) {
          item.style.lineHeight = "5rem";
      });
  } else {
      document.getElementById("header").style.height = "7rem";
      document.getElementById("logo-personal").style.marginLeft = "initial";
      document.querySelector(".menu").style.marginRight = "initial"; 
      const listItems = document.querySelectorAll("li");
      listItems.forEach(function(item) {
          item.style.lineHeight = "7rem";
      });
  }
}


//nav//
function openMenu() {
  document.getElementsByClassName('menu')[0].style.right = "0"; // Trượt menu vào
  document.getElementsByClassName('icon-nav')[0].style.display ="none";
}

function closeMenu() {
  document.getElementsByClassName('menu')[0].style.right = "-100%"; // Trượt menu ra ngoài
  setTimeout(() => {
    document.getElementsByClassName('icon-nav')[0].style.display = "block"; // Hiển thị lại icon sau khi menu đã đóng hoàn toàn
}, 300); 
}

  