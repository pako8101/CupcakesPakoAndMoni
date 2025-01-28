function updateQuantity(inputElement) {
    const id = inputElement.getAttribute("data-id");
    const quantity = inputElement.value;

    fetch(`/cart/update/${id}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ quantity: quantity }),
    })
        .then(response => {
            if (response.ok) {
                window.location.reload();
            } else {
                alert("Неуспешно обновяване на количеството.");
            }
        });
}

function removeFromCart(buttonElement) {
    const id = buttonElement.getAttribute("data-id");

    fetch(`/cart/remove/${id}`, {
        method: 'POST',
    })
        .then(response => {
            if (response.ok) {
                window.location.reload();
            } else {
                alert("Неуспешно премахване на артикула.");
            }
        });
}

function checkout() {
    fetch(`/cart/checkout`, {
        method: 'POST',
    })
        .then(response => {
            if (response.ok) {
                alert("Поръчката е успешно завършена!");
                window.location.href = "/";
            } else {
                alert("Неуспешно завършване на поръчката.");
            }
        });
}