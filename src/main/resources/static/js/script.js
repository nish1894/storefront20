console.log("script loaded")

let currentTheme = getTheme();
// console.log(currentTheme);
changeTheme(currentTheme);



//fucntion to change theme
function changeTheme() {
    //set to web page
  
    changePageTheme(currentTheme, "");
    //set the listener to change theme button
    const changeThemeButton = document.querySelector("#theme_change_button");
  
    changeThemeButton.addEventListener("click", (event) => {
      let oldTheme = currentTheme;
      console.log("change theme button clicked");
      if (currentTheme === "dark") {
        //theme ko light
        currentTheme = "light";
      } else {
        //theme ko dark
        currentTheme = "dark";
      }
      console.log(currentTheme);
      changePageTheme(currentTheme, oldTheme);
    });
  }

// set theme to local storage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

// get theme from local storage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}


// change current page theme 
function changePageTheme(theme, oldTheme) {
    //localstorage mein update karenge
    setTheme(currentTheme);
    //remove the current theme
  
    if (oldTheme) {
      document.querySelector("html").classList.remove(oldTheme);
    }
    //set the current theme
    document.querySelector("html").classList.add(theme);
  
    // change the text of button
    document
      .querySelector("#theme_change_button")
      .querySelector("span").textContent = theme == "light" ? "Dark" : "Light";
  }

  function togglePassword(id) {
    const input = document.getElementById(id);
    input.type = input.type === "password" ? "text" : "password";
}
