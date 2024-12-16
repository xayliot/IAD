FriendList();
CongratList();

var friendsData;
var catheditem;
function SendEmailCheck() {

    var to = document.getElementById('to').value;
    var subject = document.getElementById('subject').value;
    var message = document.getElementById('message').value;


    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(to)) {
        alert('Пожалуйста, введите корректный email.');
        return;
    }


    if (subject.length < 5 || subject.length > 20) {
        alert('Тема должна содержать от 5 до 20 символов.');
        return;
    }


    if (message.length < 10 || message.length > 100) {
        alert('Сообщение должно содержать от 10 до 100 символов.');
        return;
    }


    SendEmail();
    CongratListaddclear();
}
function AddFriendCheck() {

    var friend_email = document.getElementById('friend_email').value;
    var surname = document.getElementById('friend_surname').value;
    var dateofbirth = document.getElementById('friend_date_of_birth').value;
    var name = document.getElementById('friend_name').value;


    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    var dateRegex = /^\d{2}\.\d{2}\.\d{4}$/;

    if (!emailRegex.test(friend_email)) {
        alert('Пожалуйста, введите корректный email.');
        return;
    }


    if (surname.length < 3 || surname.length > 20) {
        alert('Фамилия должна быть от 3 до 20 символов.');
        return;
    }

    if (!dateRegex.test(dateofbirth)) {
        alert('Пожалуйста, введите корректную дату рождения.');
        return;
    }

    if (name.length < 3 || name.length > 15) {
        alert('Имя должно быть от 3 до 20 символов.');
        return;
    }


    AddFriend();
}
function openContent(contentId) {
    var content = document.getElementById(contentId);
    var contents = document.getElementsByClassName('content');
    for (var i = 0; i < contents.length; i++) {
        contents[i].style.display = 'none';
    }
    content.style.display = 'block';
}

function openPopup() {
    var popup = document.getElementById("success-popup");
    popup.style.display = "block";
}
function openMenu() {




    var sidebar = document.getElementById("menu");

    if (sidebar.classList.contains("open")) {
        sidebar.classList.remove("open");
        var contents = document.getElementsByClassName('content');
        for (var i = 0; i < contents.length; i++) {
            contents[i].style.display = 'none';
        }
    } else {
        sidebar.classList.add("open");
        sidebar.classList.remove("closed");
    }

}
function bigButtonClose() {
    var sidebar = document.getElementById("menu");
    var contents = document.getElementsByClassName('content');
    for (var i = 0; i < contents.length; i++) {
        contents[i].style.display = 'none';
    }
    sidebar.classList.remove("open");
}
function closeMenu() {

    var sidebar = document.getElementById("menu");
    var contents = document.getElementsByClassName('content');
    for (var i = 0; i < contents.length; i++) {
        contents[i].style.display = 'none';
    }
    if (sidebar.classList.contains("open")) {
        sidebar.classList.remove("open");
        sidebar.classList.add("closed");
    } else {
        sidebar.classList.remove("closed");
        sidebar.classList.add("open");
    }

    var popup = document.getElementById("firstbutton");
    popup.style.display = "block";
}

function closePopup() {
    var popup = document.getElementById("success-popup");
    popup.style.display = "none";
}
function CongratList() {

    var friendsList = document.getElementById("congrat-list");
    //friendsList.innerHTML = "";
    fetch("/api/congrats/showCongrats")
        .then(function (response) {
            if (response.status === 200) {
                return response.json(); // Возвращает Promise с JSON-данными
            } else {
                throw new Error("Произошла ошибка при получении данных.");
            }
        })
        .then(function (congrats) {
            var congratList = document.getElementById("congrat-list");

            congrats.forEach(function (congrat) {
                var title = congrat.title;
                var li = document.createElement("li"); // Создаем новый элемент li
                li.textContent = title; // Устанавливаем текст элемента li на имя друга

                congratList.appendChild(li); // Добавляем элемент li в список друзей
            });
        })
        .catch(function (error) {
            console.error(error.message); // Обработка ошибок
        });

}

function CongratListaddclear() {

    var friendsList = document.getElementById("congrat-list");
    friendsList.innerHTML = "";
    fetch("/api/congrats/showCongrats")
        .then(function (response) {
            if (response.status === 200) {
                return response.json(); // Возвращает Promise с JSON-данными
            } else {
                throw new Error("Произошла ошибка при получении данных.");
            }
        })
        .then(function (congrats) {
            var congratList = document.getElementById("congrat-list");

            congrats.forEach(function (congrat) {
                var title = congrat.title;
                var li = document.createElement("li"); // Создаем новый элемент li
                li.textContent = title; // Устанавливаем текст элемента li на имя друга

                congratList.appendChild(li); // Добавляем элемент li в список друзей
            });
        })
        .catch(function (error) {
            console.error(error.message); // Обработка ошибок
        });

}


function AddFriend() {

    // Создайте объект для отправки данных в формате JSON
    const formData = new FormData();
    formData.append("friend_email", document.getElementById("friend_email").value);
    formData.append("friend_surname", document.getElementById("friend_surname").value);
    formData.append("friend_date_of_birth", document.getElementById("friend_date_of_birth").value);
    formData.append("friend_name", document.getElementById("friend_name").value);

    // Преобразуйте FormData в объект JavaScript
    const formDataObject = {};
    formData.forEach((value, key) => {
        formDataObject[key] = value;
    });
    console.log(formDataObject)
    // Отправьте данные в формате JSON
    fetch('/api/friends/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded', // Измените Content-Type
        },
        body: new URLSearchParams(formDataObject).toString(), // Используйте URLSearchParams для сериализации данных формы
    })
        .then(response => {
            if (response.ok) {
                return response.json();

            } else {
                console.log('Ошибка при отправке данных на сервер');
                throw new Error('Ошибка при отправке данных на сервер');
            }
        })
        .then(data => {
            console.log(data.message);
            document.getElementById("success-message").textContent = data.message;
           // FriendPopupclose();
         //   FriendSuccessPopup();
            FriendListaddcear();
        });
}

function FriendList() {
   // openContent('content1');
    var friendsList = document.getElementById("friends-list");
  //  friendsList.innerHTML = "";
    fetch("/api/friends/showFriends")
        .then(function (response) {
            if (response.status === 200) {
                return response.json(); // Возвращает Promise с JSON-данными
            } else {
                throw new Error("Произошла ошибка при получении данных.");
            }
        })
        .then(function (friends) {
            var friendsList = document.getElementById("friends-list");

            friends.forEach(function (friend) {
                var friendName = friend.friendName;
                var li = document.createElement("li"); // Создаем новый элемент li
                li.textContent = friendName; // Устанавливаем текст элемента li на имя друга

                friendsList.appendChild(li); // Добавляем элемент li в список друзей
            });
        })
        .catch(function (error) {
            console.error(error.message); // Обработка ошибок
        });

}

function FriendListaddcear() {
    // openContent('content1');
    var friendsList = document.getElementById("friends-list");
      friendsList.innerHTML = "";
    fetch("/api/friends/showFriends")
        .then(function (response) {
            if (response.status === 200) {
                return response.json(); // Возвращает Promise с JSON-данными
            } else {
                throw new Error("Произошла ошибка при получении данных.");
            }
        })
        .then(function (friends) {
            var friendsList = document.getElementById("friends-list");

            friends.forEach(function (friend) {
                var friendName = friend.friendName;
                var li = document.createElement("li"); // Создаем новый элемент li
                li.textContent = friendName; // Устанавливаем текст элемента li на имя друга

                friendsList.appendChild(li); // Добавляем элемент li в список друзей
            });
        })
        .catch(function (error) {
            console.error(error.message); // Обработка ошибок
        });

}

function FriendListDropDown() {
    openContent('content3');
    CongratList();

    fetch("/api/friends/showFriends")
        .then(function (response) {
            if (response.status === 200) {
                return response.json();
            } else {
                throw new Error("Произошла ошибка при получении данных.");
            }
        })
        .then(function (friends) {
            friendsData = friends;

            var friendDropdown = document.getElementById("friendlst");

            friendDropdown.innerHTML = "";

            friends.forEach(function (friend) {
                var friendName = friend.friendName;
                var friendSurname = friend.friendSurname;
                var option = document.createElement("option");
                option.value = friendName;
                option.text = friendName + " " + friendSurname ;

                friendDropdown.appendChild(option);
            });
        })
        .catch(function (error) {
            console.error(error.message);
        });
}

function SendEmail() {


    // Создайте объект для отправки данных в формате JSON
    const formData = new FormData();
    formData.append("to", document.getElementById("to").value);
    formData.append("subject", document.getElementById("subject").value);
    formData.append("message", document.getElementById("message").value);
    formData.append("date", document.getElementById("dateTimePicker2").value);

    const formDataObject = {};
    formData.forEach((value, key) => {
        formDataObject[key] = value;
    });
    console.log(formDataObject)
    //showHiddenMessage();
    console.log(formDataObject)
    fetch('/api/emails/send', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded', // Измените Content-Type
        },
        body: new URLSearchParams(formDataObject).toString(), // Используйте URLSearchParams для сериализации данных формы
    })

        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                console.log('Ошибка при отправке данных на сервер');
                throw new Error('Ошибка при отправке данных на сервер');
            }
        })
        .then(data => {
            document.getElementById("success-message").textContent = data.message;
            console.log(data.message);
            //EmailSentPopUpclose();
            //closeHiddenMessage();
            //openPopup();


        });
}

function findItemByNameAndSurname(name, surname) {
    // Предполагается, что friendsData - это массив JSON объектов
    for (var i = 0; i < friendsData.length; i++) {
        var currentItem = friendsData[i];
        if (currentItem.friendName === name && currentItem.friendSurname === surname) {
            return currentItem;

        }

    }


}




function CongratSend() {


    var selectedText = document.getElementById("friendlst").options[document.getElementById("friendlst").selectedIndex].text;
    var parts = selectedText.split(' ');
    var name = parts[0];
    var surname = parts[1];
    var data = findItemByNameAndSurname(name, surname);
    const selectedDateTime = document.getElementById('dateTimePicker').value;

    const formData = new FormData();
    formData.append("title", document.getElementById("title").value);
    formData.append("text", document.getElementById("text").value);
    formData.append("email", document.getElementById("attachment").value);
    formData.append('date', selectedDateTime);
    var id = data.id;
    console.log(id)
    var id = data.id.toString();
    formData.append("friend_id", id);


    const formDataObject = {};
    formData.forEach((value, key) => {
        formDataObject[key] = value;
    });
    //showHiddenMessage();


    fetch('/api/congrats/send', {
        method: 'POST',
        body: formData, // Используйте URLSearchParams для сериализации данных формы
    })

        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                console.log('Ошибка при отправке данных на сервер');
                throw new Error('Ошибка при отправке данных на сервер');
            }
        })
        .then(data => {
            document.getElementById("success-message").textContent = data.message;
            console.log(data.message);
            EmailSentPopUpclose();
            closeHiddenMessage();
            openPopup();
            CongratListaddclear();
        });
}



function FriendPopup() {
    var popup = document.getElementById("add-friend");
    popup.style.display = "block";
}

function FriendSuccessPopup() {
    var popup = document.getElementById("friend_success");
    popup.style.display = "block";
}
function FriendSuccessPopupclose() {
    var popup = document.getElementById("friend_success");
    popup.style.display = "none";
    var inputfriend_email = document.getElementById("friend_email");
    var inputfriend_surname = document.getElementById("friend_surname");
    var inputfriend_date_of_birth = document.getElementById("friend_date_of_birth");
    var inputfriend_name = document.getElementById("friend_name");
    inputfriend_email.value = "";
    inputfriend_surname.value = "";
    inputfriend_date_of_birth.value = "";
    inputfriend_name.value = "";

}

function FriendPopupclose() {
    var popup = document.getElementById("add-friend");
    popup.style.display = "none";
    var inputfriend_email = document.getElementById("friend_email");
    var inputfriend_surname = document.getElementById("friend_surname");
    var inputfriend_date_of_birth = document.getElementById("friend_date_of_birth");
    var inputfriend_name = document.getElementById("friend_name");
    inputfriend_email.value = "";
    inputfriend_surname.value = "";
    inputfriend_date_of_birth.value = "";
    inputfriend_name.value = "";
}

function EmailSentPopUp() {
    var popup = document.getElementById("email-sent");
    popup.style.display = "block";
}
function DateTimeButton(){
    var myButton = document.getElementById("datetimebutton");
    myButton.style.display = "none";
    document.getElementById("dateTimePicker").removeAttribute("hidden");
}
function DateTimeButton2(){
    var myButton = document.getElementById("datetimebutton2");
    myButton.style.display = "none";
    document.getElementById("dateTimePicker2").removeAttribute("hidden");
}
function EmailSentPopUpclose() {
    var popup = document.getElementById("email-sent");
    popup.style.display = "none";
    var myButton = document.getElementById("datetimebutton2");
    myButton.style.display = "block";
    document.getElementById("dateTimePicker2").setAttribute("hidden", true);
}

function showHiddenMessage() {
    var hiddenMessage = document.getElementById("loading-indicator");
    hiddenMessage.style.display = "block";
}
function closeHiddenMessage() {
    var hiddenMessage = document.getElementById("loading-indicator");
    hiddenMessage.style.display = "none";
}
function CongratSentPopUp() {
    var hiddenMessage = document.getElementById("congrat-sent");
    hiddenMessage.style.display = "block";
}
function CongratSentPopUpclose() {
    var hiddenMessage = document.getElementById("congrat-sent");
    hiddenMessage.style.display = "none";
    var myButton = document.getElementById("datetimebutton");
    myButton.style.display = "block";
    document.getElementById("dateTimePicker").setAttribute("hidden", true);
}

function SendMessage() {
    event.preventDefault();
    var to = document.getElementById("to").value;
    var subject = document.getElementById("subject").value;
    var message = document.getElementById("message").value;
}
function logout() {
    fetch("/logout", { method: "POST" })
        .then(response => {
            if (response.redirected) {
                window.location.href = response.url;
            }
        });
}