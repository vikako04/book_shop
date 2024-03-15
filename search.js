const content = document.querySelector('.container');

const form = document.createElement('form');
form.classList.add('search');
form.addEventListener('submit',(e) => {
    e.preventDefault();
    const inputValue = Object.fromEntries(new FormData(e.target));
    const books = document.querySelectorAll('.item');
    books.forEach(book => {
        const title = book.querySelector('h4').textContent.trim().toLowerCase();
        const bookContainer = book.closest('.col-lg-4'); 
        if (title.includes(inputValue.name.trim().toLowerCase())) {
            bookContainer.style.display = 'block'; 
        } else {
            bookContainer.style.display = 'none'; 
        }
    });
});

const input = document.createElement('input');
input.classList.add('search-input');
input.setAttribute('name','name');

const button = document.createElement('button');
button.classList.add('search-button');
button.setAttribute('type','submit');
button.innerHTML = "Поиск";

form.appendChild(input);
form.appendChild(button);
content.appendChild(form);

// Создаем чекбокс "от 0 до 20$"
const label1 = document.createElement('label'); 
label1.classList.add('checkbutton');
label1.innerHTML = "от 0 до 20$"; 
const checkButton1 = document.createElement('input');
checkButton1.setAttribute('type','checkbox');
checkButton1.setAttribute('id','priceFilter1'); 
label1.setAttribute('for', 'priceFilter1'); 

// Создаем чекбокс "от 21$ и выше"
const label2 = document.createElement('label'); 
label2.classList.add('checkbutton');
label2.innerHTML = "от 21$ и выше"; 
const checkButton2 = document.createElement('input');
checkButton2.setAttribute('type','checkbox');
checkButton2.setAttribute('id','priceFilter2'); 
label2.setAttribute('for', 'priceFilter2'); 

content.appendChild(checkButton1);
content.appendChild(label1); 
content.appendChild(checkButton2);
content.appendChild(label2); 

// Обработчик изменения состояния первого чекбокса
function filterBooks1() {
    const isChecked1 = checkButton1.checked;
    const isChecked2 = checkButton2.checked;

    const books = document.querySelectorAll('.item');
    books.forEach(book => {
        const price = parseFloat(book.querySelector('.down-content span').textContent.replace('$', ''));
        const bookContainer = book.closest('.col-lg-4'); 

        if (isChecked1 && price <= 20) {
            bookContainer.style.display = 'block'; 
        } else if (!isChecked1 && !isChecked2) {
            bookContainer.style.display = 'block'; 
        } else {
            bookContainer.style.display = 'none'; 
        }
    });
}

// Обработчик изменения состояния второго чекбокса
function filterBooks2() {
    const isChecked1 = checkButton1.checked;
    const isChecked2 = checkButton2.checked;

    const books = document.querySelectorAll('.item');
    books.forEach(book => {
        const price = parseFloat(book.querySelector('.down-content span').textContent.replace('$', ''));
        const bookContainer = book.closest('.col-lg-4'); 

        if (isChecked2 && price >= 21) {
            bookContainer.style.display = 'block'; 
        } else if (!isChecked1 && !isChecked2) {
            bookContainer.style.display = 'block'; 
        } else {
            bookContainer.style.display = 'none'; 
        }
    });
}

checkButton1.addEventListener('change', filterBooks1);
checkButton2.addEventListener('change', filterBooks2);
