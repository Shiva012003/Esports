const form = document.getElementById("teamForm");

if(form){

    form.addEventListener("submit", async function(e){

        e.preventDefault();

        const data = {

            tournamentType: "BR",

            teamName:
            document.getElementById("teamName").value,

            leaderName:
            document.getElementById("leaderName").value,

            whatsapp:
            document.getElementById("whatsapp").value,

            email:
            document.getElementById("email").value,

            player2:
            document.getElementById("player2").value,

            player3:
            document.getElementById("player3").value,

            player4:
            document.getElementById("player4").value
        };

        try{

            const response = await fetch(
                API_BASE + "/teams",
                {

                    method:"POST",

                    headers:{
                        "Content-Type":"application/json"
                    },

                    body:JSON.stringify(data)
                }
            );

            if(response.ok){

                alert("BR Registration Successful!");

                window.location.href = "/payment.html";

            } else {

                alert("Registration Failed");
            }

        } catch(error){

            console.log(error);

            alert("Server Error");
        }
    });
}

const csForm = document.getElementById("csForm");

if(csForm){

    csForm.addEventListener("submit", async function(e){

        e.preventDefault();

        const data = {

            tournamentType: "CS",

            teamName:
            document.getElementById("csTeamName").value,

            leaderName:
            document.getElementById("csLeaderName").value,

            whatsapp:
            document.getElementById("csWhatsapp").value,

            email:
            document.getElementById("csEmail").value,

            player2:
            document.getElementById("csPlayer2").value,

            player3:
            document.getElementById("csPlayer3").value,

            player4:
            document.getElementById("csPlayer4").value
        };

        try{

            const response = await fetch(
                API_BASE + "/teams",
                {

                    method:"POST",

                    headers:{
                        "Content-Type":"application/json"
                    },

                    body:JSON.stringify(data)
                }
            );

            if(response.ok){

                alert("CS Registration Successful!");

                window.location.href = "/payment.html";

            } else {

                alert("Registration Failed");
            }

        } catch(error){

            console.log(error);

            alert("Server Error");
        }
    });
}

async function uploadScreenshot(){

    const fileInput =
        document.getElementById("screenshot");

    const formData = new FormData();

    formData.append(
        "file",
        fileInput.files[0]
    );

    try{

        const response = await fetch(
            API_BASE + "/teams/upload",
            {

                method:"POST",

                body:formData
            }
        );

        if(response.ok){

            alert("Payment Screenshot Uploaded!");

        } else {

            alert("Upload Failed");
        }

    } catch(error){

        console.log(error);

        alert("Server Error");
    }
}