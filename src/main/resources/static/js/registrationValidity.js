 function register(){
    if(checkValidity())
        document.getElementById("hiddenSubmit").click();
 }

 function checkValidity(){
      let validity = true;
      let user=getUserData();
      let errorSection= document.getElementById("errors");
      errorSection.innerText="";
      let rules=[loginLengthRule(user),passwordRepeatEqualityRule(user),passwordLengthRule(user),emailFormatRule(user)]

        for(let rule of rules){
            if(!rule.validityCondition){
               unhideErrorSection();
               let errorElement= document.createElement("li");
               errorElement.innerText= rule.message;
               errorSection.appendChild(errorElement);
               validity=false;
            }
        }
       return validity;
   }

   function getUserData(){
    return {login: document.getElementById("username").value,
            password: document.getElementById("password").value,
            passRepeat:document.getElementById("password-repeat").value,
            email:document.getElementById("email").value
            }
   }
   function unhideErrorSection(){
   Array.from(document.getElementsByClassName("errors")).forEach(el=>el.hidden=false)
   }

   function loginLengthRule(user){
    return {message:"Login must contain minimum 3 characters",validityCondition: user.login.length >=3}
   }

   function passwordLengthRule(user){
       return {message:"Password must contain minimum 6 characters",validityCondition: user.password.length >=6}
   }

   function passwordRepeatEqualityRule(user){
           return {message:"Password and Password Repeat are not the same",validityCondition: user.password==user.passRepeat}
   }
   function emailFormatRule(user)
   {
    return {message:"Email has invalid format",validityCondition: /\S+@\S+\.\S+/.test(user.email)}
   }
