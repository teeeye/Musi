var _file_name = "";
var MAX_FILE_SIZE = 10240000;

$(document).ready(function(){
    $('.upload-button').click(select_file);
});

function select_file() {
    if ($('.upload-button').hasClass('upload-button__uploading') || $('.upload-button').hasClass('upload-button__succeed') || $('.upload-button').hasClass('upload-button__failed')) {
        return;
    }
    $('#file-selector').trigger('click');
}

function file_did_select() {
	$('.upload-button').addClass('upload-button__uploading');
    var file = document.getElementById('file-selector').files[0];
    if (file != null) {
        _file_name = file.name;
        if (file.size > MAX_FILE_SIZE) {
            alert('文件太大！（ 不应超过10MB ）');
            return;
        }
    }
    var reader = new FileReader();
    reader.onload = file_did_load;
    reader.readAsDataURL(file);
}

function file_did_load() {
    var result = this.result;
    var index = result.search(/base64,/i);
    var base64result = result.substring(index+7);
    upload_music(base64result);
}

function upload_music(file_data) {	
    $.ajax({
        url: 'upload',
        data: {
        		msc_name: ""+_file_name+"",
        		msc_data: "\""+file_data+"\""
        },
        type: 'POST',
        success: function(result) {
            json = JSON.parse(result);
            if (json != null && json.success != null && json.success == true) {
                upload_succeed();
            } else if (json != null) {
                error_message = json.reason | 'Internet Error!';
                upload_failed(error_message);
            }
        }
    });
}

function upload_succeed() {
    $('.upload-button').addClass('upload-button__succeed');
}

function upload_failed(msg) {
    $('.upload-button').addClass('upload-button__failed');
}