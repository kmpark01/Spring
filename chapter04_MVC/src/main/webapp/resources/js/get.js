//-----CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/get.css';
// 2. link 태그 생성
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.href = CSS_FILE_PATH;
// 3. head 태그에 link 엘리먼트 추가
document.head.appendChild(linkEle);

let f = document.forms[0];
let pageNum = new URLSearchParams(location.search).get('pageNum');
let amount = new URLSearchParams(location.search).get('amount');

console.log(pageNum);
console.log(amount);
// 각 버튼 클릭 이벤트
document.querySelectorAll('button').forEach(btn => {
  btn.addEventListener('click', () => {
    let type = btn.getAttribute("id");
    let bno = f.bno.value;
    

    if(type === 'modifyBtn'){
      location.href = `/board/modify?bno=${bno}&pageNum=${pageNum}&amount=${amount}`;
    }else if(type === 'indexBtn'){
      // url에서 pageNum, amount 가져오기
      location.href = `/board/list?pageNum=${pageNum}&amount=${amount}`;
    }
    
  });
});
