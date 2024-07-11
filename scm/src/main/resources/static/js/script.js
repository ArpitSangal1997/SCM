console.log("running");

let currentTheme = getTheme();
//initial
document.addEventListener('DOMContentLoaded', () => {
    changeTheme();

});


function changeTheme(){
//set to web page
    changePageTheme(currentTheme,currentTheme);

    document.querySelector("html").classList.add(currentTheme);
}
//setup a listner to change theme by button
const themechangebutton = document.querySelector("#theme_change_button");
       
themechangebutton.addEventListener('click', (event)=> {
    let oldTheme = currentTheme;
    if(currentTheme==="dark")
        currentTheme ="light";
    else
     currentTheme= "dark";

     changePageTheme(currentTheme, oldTheme);
} );

//set theme to local storage
function setTheme(theme) {
    localStorage.setItem("theme",theme);
}

//get theme to local storage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}

function changePageTheme(theme, oldTheme){
    //updqating in local storage
    setTheme(currentTheme);
    //removing theme
    document.querySelector('html').classList.remove(oldTheme);
    //update in local storage
   document.querySelector('html').classList.add(theme);
       //change content of button
       document.querySelector('#theme_change_button').querySelector("span").textContent =
       theme == "light" ? "Dark": "Light";

}