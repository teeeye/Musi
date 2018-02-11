var _is_playing = false;

$(document).ready(function(){
	$('.audit-play').click(play);
	$('.audit-reject').click(reject);
	$('.audit-pass').click(pass);
	getAuditList();
});

function getAuditList() {
	$.ajax({
		url: 'audit/query',
		success: function(data) {
			json = JSON.parse(data);
			if (json.success == false) {
				alert(json.reason);
			} else {
				for (var i = 0; i < json.data.length; i++) {
					var audit_item = json.data[i];
					$('.audit-list').append('<li class="audit-item audit-item_'+audit_item.msc_id+'" msc_name="'+audit_item.msc_name+'" msc_id="'+audit_item.msc_id+'" msc_path="'+audit_item.msc_path+'"><span class="audit-item--title">'+audit_item.msc_name+'</span><span class="audit-item--id">'+audit_item.msc_id+'</span></li>');
				}
				
				$('.audit-item').click(function(){
					var msc_id = $(this).attr('msc_id');
					var msc_name = $(this).attr('msc_name');
					var msc_path = $(this).attr('msc_path');
					loadAuditItem(msc_id, msc_name, msc_path);
				});
			}
		}
	});
}

function loadAuditItem(id, name, path) {
	$('.audit-name').val(name);
	$('.audit-id').val(id);
	$('#audit-audio').attr('src', path);
}

function play() {
	var audio = document.getElementById('audit-audio');
	if (!_is_playing) {
		audio.play();
		$('.audit-play').html('暂停');
	} else {
		audio.pause();
		$('.audit-play').html('播放');
	}
	_is_playing = !_is_playing;
}

function reject() {
	var msc_path = $('.audit-item_'+msc_id).attr('msc_path');
	$.ajax({
		url: 'audit/submit',
		data: {
			msc_id: $(".audit-id").val(),
			msc_path: msc_path,
			adt_pass: false
		},
		success: function(data) {
			var json = JSON.parse(data);
			if (json.success == true) {
				removeItem();
			} else {
				alert(json.reason);
			}
		}
	});
}

function pass() {
	
	var msc_id = $(".audit-id").val();
	var msc_path = $('.audit-item_'+msc_id).attr('msc_path');
	var msc_name = $('.audit-name').val() || 'untitled';
	var msc_sngr = $('.audit-singer').val() || '';
	var msc_albm = $('.audit-playlist').val() || '';
	var msc_ctgy = $('.audit-category').val() || 0;
	
	$.ajax({
		url: 'audit/submit',
		data: {
			msc_id: msc_id,
			msc_path: msc_path,
			msc_name: msc_name,
			msc_sngr: msc_sngr,
			msc_albm: msc_albm,
			msc_ctgy: msc_ctgy,
			adt_pass: true
		},
		type: 'POST',
		success: function(data) {
			var json = JSON.parse(data);
			if (json.success == true) {
				removeItem();
			} else {
				alert(json.reason);
			}
		}
	});
}

function removeItem() {
	var id = $('.audit-id').val();
	$('.audit-id').val('');
	$('.adudit-name').val('');
	$('.audit-item_'+id).remove();
}
