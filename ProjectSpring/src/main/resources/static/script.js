//поиск
document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form');
    const searchInput = document.getElementById('search');
    const books = document.querySelectorAll('.book');
    const container = document.querySelector('.container');

    form.addEventListener('submit', function(event) {
        event.preventDefault();
        const searchTerm = searchInput.value.trim().toLowerCase();
        let booksFound = false;

        books.forEach(function(book) {
            const title = book.querySelector('h2').textContent.trim().toLowerCase();
            const author = book.querySelector('p').textContent.trim().toLowerCase();

            if (title.includes(searchTerm) || author.includes(searchTerm)) {
                book.style.display = 'block';
                booksFound = true;
            } else {
                book.style.display = 'none';
            }
        });

        if (!booksFound) {
            container.style.height = '315px';
        } 
        else 
        {
            container.style.height = 'auto';
        }
    });
});


//корзина
document.addEventListener("DOMContentLoaded", function() {
    
    //удаление
    // var removeButtons = document.querySelectorAll(".remove-btn");
    // removeButtons.forEach(function(button) {
    //     button.addEventListener("click", function() {
    //         var item = this.closest(".item");
    //         item.parentNode.removeChild(item);
    //         calculateTotal();
    //     });
    // });

    //изменение количества
    var quantityButtons = document.querySelectorAll(".quantity button");
    quantityButtons.forEach(function(button) {
        button.addEventListener("click", function() {
            var span = this.parentNode.querySelector("span");
            var quantity = parseInt(span.textContent);
            if (this.textContent === "-") {
                if (quantity > 1) {
                    quantity--;
                }
            } else {
                quantity++;
            }
            span.textContent = quantity;
            calculateTotal();
            updateItemCost(this.closest(".item"), quantity);
        });
    });

    //общая сумма
    function calculateTotal() {
        var items = document.querySelectorAll(".item");
        var total = 0;
        items.forEach(function(item) {
            var price = parseFloat(item.querySelector(".item-details p.bookprice").textContent);
            var quantity = parseInt(item.querySelector(".quantity span").textContent);
            total += price * quantity;
        });
        document.getElementById("totalPrice").textContent = total + " тг";
        document.getElementById("totalAmount").value = total;
    }

    document.getElementById("checkoutForm").addEventListener("submit", function(event) {
        calculateTotal(); // Обновление общей суммы перед отправкой формы
    });

    //стоимость в зависимости от количества
    function updateItemCost(item, quantity) {
        var price = parseFloat(item.querySelector(".item-details p.bookprice").textContent);
        var itemCost = price * quantity;
        item.querySelector(".item-cost").textContent = itemCost + " тг";
    }

    calculateTotal();
});




document.addEventListener('DOMContentLoaded', function() {
    const filterBtn = document.querySelector('.filter-btn');
    const filterPopup = document.querySelector('.filter-popup');
    const applyFilterBtn = document.getElementById('apply-filter-btn');

    // Показать/скрыть всплывающее окно при клике на кнопку фильтра
    filterBtn.addEventListener('click', function() {
        if (filterPopup.style.display === 'none') {
            filterPopup.style.display = 'block';
        } else {
            filterPopup.style.display = 'none';
        }
    });

    // Применить фильтр и скрыть всплывающее окно
    applyFilterBtn.addEventListener('click', function() {
        // Здесь можно получить значения фильтров и применить их к результатам поиска
        filterPopup.style.display = 'none';
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const priceFromInput = document.getElementById('price-from');
    const priceToInput = document.getElementById('price-to');
    const priceFromValue = document.getElementById('price-from-value');
    const priceToValue = document.getElementById('price-to-value');

    priceFromInput.addEventListener('input', function() {
        priceFromValue.textContent = this.value;
    });

    priceToInput.addEventListener('input', function() {
        priceToValue.textContent = this.value;
    });
});



document.getElementById('add-balance').addEventListener('click', function() {
    document.getElementById('modal').style.display = 'block';
});

document.getElementsByClassName('close')[0].addEventListener('click', function() {
    document.getElementById('modal').style.display = 'none';
});



