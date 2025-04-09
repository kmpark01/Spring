//-----CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/modify.css';
// 2. link 태그 생성
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.href = CSS_FILE_PATH;
// 3. head 태그에 link 엘리먼트 추가
document.head.appendChild(linkEle);

const f = document.forms[0];
let pageNum = new URLSearchParams(location.search).get('pageNum');
let amount = new URLSearchParams(location.search).get('amount');
let bno = new URLSearchParams(location.search).get('bno');

//각 버튼 클릭 이벤트
document.querySelectorAll('button').forEach(btn => {
  btn.addEventListener('click', () => {
    let type = btn.getAttribute("id");
    let bno = f.bno.value;

    if(type === 'modifyBtn'){
      modify();
    }else if(type === 'removeBtn'){
      remove();
    }else if(type === 'indexBtn'){
    	location.href = `/board/list?pageNum=${pageNum}&amount=${amount}`;
    }
  });
});

//------------------- 첨부 파일 스크립트-------------------
let uploadDiv = document.querySelector('.uploadDiv');
let cloneObj = uploadDiv.firstElementChild.cloneNode(true);

const regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
const MAX_SIZE = 5242880; //5MB
const fileInput = document.querySelector('input[type="file"]');

fileInput.addEventListener('change', () => {
  const formData = new FormData();
  const inputFile = document.querySelector('input[type="file"]');
  const files = inputFile.files;

  // file 객체들을 formData에 담기
  for(let i = 0; i < files.length; i++){
    if(!checkExtension(files[i].name, files[i].size)){
      return false;
    }
    formData.append("uploadFile", files[i]);
  }

  fetch(`/uploadAsyncAction`, {
    method : 'post',
    body : formData
  })
    .then(response => response.json())
    .then(data => {
      console.log(data);

      // 부모 Element.replaceChild(newElement, oldElement)
//      uploadDiv.replaceChild(
//        cloneObj.cloneNode(true), 
//        uploadDiv.firstElementChild);
      fileInput.value = '';
      
      showUploadFiles(data);
    })
    .catch(err => console.log(err));
})

function checkExtension(fileName, fileSize){
  if(fileSize >= MAX_SIZE){
    alert("파일 사이즈 초과");
    return false;
  }

  if(regex.test(fileName)){
    alert("해당 종류의 파일은 업로드할 수 없습니다.");
    return false;
  }
  return true;
}
fetch(`/board/getAttachList/${bno}`)
  .then(response => response.json())
  .then(result => {
    console.log(result);
    showUploadFiles(result);
  })
  .catch(err => console.log(err));

let uploadFilePath = document.querySelector('.uploadResult ul');
function showUploadFiles(uploadResultArr){
  if(!uploadResultArr || uploadResultArr.length == 0){
    return;
  }
  let str = '';
  uploadResultArr.forEach(file => {
    const fileCallPath = encodeURIComponent(`${file.uploadPath}/s_${file.uuid}_${file.fileName}`);
    str += `<li path="${file.uploadPath}" uuid="${file.uuid}" fileName="${file.fileName}">`;
//    str += `<a href="/download?fileName=${fileCallPath}">${file.fileName}</a>`;
     str += `<a>${file.fileName}</a>`;
     str += `<span data-file=${fileCallPath}> X </span>`;
    str += `</li>`;
  });
  uploadFilePath.insertAdjacentHTML('beforeend', str);
}

// 게시글 수정 이벤트
function modify(){
  if(!f.title.value){
    alert("제목을 입력하세요.");
    return;
  }

  if(!f.content.value){
    alert("내용을 입력하세요.");
    return;
  }
  
  // 첨부파일 li들 가져와서 반복문 돌리기
  // 해당 li에 포함된 속동들 꺼내서 화면에 출력
  let str = '';
  document.querySelectorAll('.uploadResult ul li').forEach((li, index) => {
    let path = li.getAttribute('path');
    let uuid = li.getAttribute('uuid');
    let fileName = li.getAttribute('fileName');
    
    str += `<input type="hidden" name="attachList[${index}].fileName" value="${fileName}">`;
    str += `<input type="hidden" name="attachList[${index}].uuid" value="${uuid}">`;
    str += `<input type="hidden" name="attachList[${index}].uploadPath" value="${path}">`;
  });
  f.insertAdjacentHTML('beforeend', str);
  
  if (deleteFiles.length > 0) {
	  fetch(`/deleteFile`, {
	    method: 'POST',
	    body: JSON.stringify(deleteFiles),  // 배열을 JSON 문자열로
	    headers: {
	      'Content-Type': 'application/json' // JSON 타입 명시
	    }
	  })
	  .then(response => response.text())
	  .then(result => {
	    console.log(result);
	    f.action = `/board/modify?pageNum=${pageNum}&amount=${amount}`;
	    f.submit();
	  })
	  .catch(err => console.log(err));
	} else {
	  f.action = `/board/modify?pageNum=${pageNum}&amount=${amount}`;
	  f.submit();
	} 
}
// 게시글 삭제 이벤트
function remove(){
  let bnoEle = f.bno; // bno를 담고 있는 input 태그
  f.innerHTML = ''; // form 태그 내부 비우기
  f.appendChild(bnoEle); // form 태그 내부에 bno 추가

  f.action = `/board/remove?pageNum=${pageNum}&amount=${amount}`;
  f.submit();
}

let deleteFiles = [];
//업로드 파일 삭제 이벤트
document.querySelector('.uploadResult ul').addEventListener('click', function(e){
	  if(e.target.tagName == 'SPAN'){
	    let targetFile = e.target.getAttribute('data-file');
      deleteFiles.push(targetFile);
      e.target.closest('li').remove();
	  }
	});