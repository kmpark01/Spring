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
      
        showUploadedFile(data);
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

let uploadResult = document.querySelector('.uploadResult ul');

function showUploadedFile(uploadResultArr){
  if(!uploadResultArr || uploadResultArr.length == 0){
    return;
  }

  let str = '';
  uploadResultArr.forEach(file => {
    let fileCallPath = encodeURIComponent(file.uploadPath + "/" + file.uuid + "_" + file.fileName);
    str += `<li path="${file.uploadPath}" uuid="${file.uuid}" fileName="${file.fileName}">`;
//    str += `<a href="/download?fileName=${fileCallPath}">${file.fileName}</a>`;
    str += `<a>${file.fileName}</a>`;
    str += `<span data-file=${fileCallPath}> X </span>`;
    str += `</li>`;
  });
  uploadResult.insertAdjacentHTML('beforeend', str);
}

uploadResult.addEventListener('click', function(e){
  if(e.target.tagName == 'SPAN'){
    let targetFile = e.target.getAttribute('data-file');

    fetch(`/deleteFile`,
      {
        method : 'post',
        body : targetFile,
        headers : {
          'Content-type' : 'text/plain'
        }
      }
    )
      .then(response => response.text())
      .then(result => {
        console.log(result);
        
        // 해당 코드 삭제
        let targetLi = e.target.closest('li');
        targetLi.remove();
      })
      .catch(err => console.log(err));
  }
});