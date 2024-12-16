function GptTest() {


    const formData = new FormData();
    formData.append("question", document.getElementById("question").value);


    const formDataObject = {};
    formData.forEach((value, key) => {
        formDataObject[key] = value;
    });


    fetch('/simplePages/gpt', {
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
            FriendPopupclose();
            FriendSuccessPopup();
        });
}