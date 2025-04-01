document.querySelectorAll('.menu a').forEach(a => {
  a.addEventListener('click', e => {
    e.preventDefault();

    let menu = e.target.getAttribute('href');

    if(menu === 'boardList'){
      location.href = '/board/list?pageNum=1&amount=10';
    }
  });
});

function setStorageData(pageNum, amount){
	let pageData = {
			pageNum : pageNum,
			amount : amount
	};
	localStorage.setItem('page_data', JSON.stringify(pageData));
}

function getStorageData(){
	return JSON.parse(localStorage.getItem('page_data'));
}