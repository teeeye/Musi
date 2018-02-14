var SEARCH_OPTION = {
		MUSIC_NAME: 0,
		SINGER_NAME: 1,
		ALBUM_NAME: 2
}

var _current_banner_index = 0;
var _banner_count = 3;
var _is_dragging = false;
var _current_progress = 0;
var _is_playing = false;
var _music_duration = 0;
var _is_login = false;
var _user_name = null;
var _user_id = null;
var _my_playlists = null;
var MAX_FILE_SIZE = 1024000;
var _current_playlist = null;
var _current_search_option = SEARCH_OPTION.MUSIC_NAME;
var _should_search_result_f7 = true;
var _ROLLING_RATE = 1;


$(document).ready(function(){
	
    setInterval(slide_banner, 3000);
    
    $('.progress-bar_container').mousedown(function(){
        _is_dragging = true;
    });
    
    $('.progress-bar_container').mousemove(drag_progress);
    
    $('.progress-bar_container').mouseup(relocate_progress);
    
    $('.header--user--login-register').click(show_login);
    
    $('.header--user--welcome').click(user_info);
    
    $('.login-cancel').click(show_login);
    
    $('.user-avatar-container').mouseover(function(){
    		$('.user-avatar--edit').addClass('user-avatar--edit__shown');
    });
    
    $('.user-avatar-container').mouseout(function(){
		$('.user-avatar--edit').removeClass('user-avatar--edit__shown');
    });
    
	$('.header--nav--item').click(header_nav);
	
	$('.header--logo').click(header_nav);
	
	$('.login-register-option').click(login_register_option);
	
	$('.login-register-button').click(login_register);
	
	$('.play-button').click(play);
	
	$('.user-name--edit').click(edit_user_name);
	
	$('.user-name--save').click(save_user_name);
	
	$('.user-avatar').click(select_file);
	
	$('.user-playlist').mouseout(playlist_mouseout);
	
	$('.user-playlist--create').click(create_playlist);
	
	$('.search-bar--option').click(search_option);
	
	$('.search-bar--button').click(search);
	
	setInterval(track_progress, 500);
	
	setInterval(roll_music_title, 50);
});

function slide_banner() {
    $('.banner_'+_current_banner_index).css({
        opacity: 0,
    });
    $('.indicator_'+_current_banner_index).removeClass('banner--indicator__highlighted');
    _current_banner_index = (_current_banner_index + 1) % _banner_count;
    $('.indicator_'+_current_banner_index).addClass('banner--indicator__highlighted');
    setTimeout(function(){
        $('.banner_'+_current_banner_index).css({
            zIndex: 201
        });
        $('.banner_'+ (_current_banner_index + 1) % _banner_count).css({
            zIndex: 200,
            opacity: 1
        });
    }, 1000);
}

function drag_progress(e) {
    if (_is_dragging) {
        _current_progress = e.clientX - $('.progress-bar_container').position().left - 8;
        _current_progress = Math.min(_current_progress, 408);
        _current_progress = Math.max(_current_progress, 8);
        $('.progress-button').css({
            left: _current_progress
        });
        $('.progress-bar_trace').css({
            width: _current_progress - 8
        });
    }
}

function relocate_progress() {
    _is_dragging = false;
    console.log((_current_progress - 8) / 4) ;
}

function show_login() {
	if ($('.login').hasClass('hidden')) {
		$('.login').removeClass('hidden');
		$('.black-background').removeClass('hidden');
	} else {
		$('.login').addClass('hidden');
		$('.black-background').addClass('hidden');	
		$('.register-option').addClass('login-register-option__selected');
		$('.login-option').removeClass('login-register-option__selected');
		$('.login-phone-number').val('');
		$('.login-password').val('');
		$('.login-register-button').removeClass('login-register-button__login');
		$('.login-register-button').addClass('login-register-button__register');
	}
}

function header_nav(){
	if ($(this).hasClass('header--logo')) {
		if (!$('.header--nav--home').hasClass('header--nav--item__selected')) {
			$('.header--nav--item__selected').removeClass('header--nav--item__selected');
			$('.header--nav--home').addClass('header--nav--item__selected');
			$('.current-view').addClass('hidden');
			$('.current-view').removeClass('current-view');
			$('.main').addClass('current-view');
			$('.main').removeClass('hidden');
			window.scrollTo(0, 0);
		}
	} else if ($(this).hasClass('header--nav--my-music') && !_is_login) {
		tips("请先登录！");
	} else if (!$(this).hasClass('header--nav--item__selected')) {
		$('.header--nav--item__selected').removeClass('header--nav--item__selected');
		$(this).addClass('header--nav--item__selected');
		$('.current-view').addClass('hidden');
		$('.current-view').removeClass('current-view');
		var currentView;
		if ($(this).hasClass('header--nav--home')) {
			currentView = $('.main');
		} else if ($(this).hasClass('header--nav--discover')) {
			currentView = $('.search');
		} else if ($(this).hasClass('header--nav--my-music')) {
			currentView = $('.user');
		}
		window.scrollTo(0, 0);
		currentView.addClass('current-view');
		currentView.removeClass('hidden');
	}
}

function user_info() {
	if (!$('.header--nav--my-music').hasClass('header--nav--item__selected')) {
		$('.header--nav--item__selected').removeClass('header--nav--item__selected');
		$('.header--nav--my-music').addClass('header--nav--item__selected');
		$('.current-view').addClass('hidden');
		$('.current-view').removeClass('current-view');
		$('.user').addClass('current-view');
		$('.user').removeClass('hidden');
	}
}

function login_register_option() {
	if ($(this).hasClass('login-register-option__selected')) {
		return;
	}
	
	$('.login-register-option__selected').removeClass('login-register-option__selected');
	$(this).addClass('login-register-option__selected');
	
	if ($(this).hasClass('login-option')) {
		$('.login-register-button').removeClass('login-register-button__register');
		$('.login-register-button').addClass('login-register-button__login');
	} else {
		$('.login-register-button').addClass('login-register-button__register');
		$('.login-register-button').removeClass('login-register-button__login');
	}
}

function login_register() {
	if (!validate_phone_number_and_password()) {
		return;
	}
	var url;
	if ($(this).hasClass('login-register-button__login')) {
		url = "login";
	} else {
		url = "register";
	}
	
	var phoneNumber = $('.login-phone-number').val();
	var password = get_MD5_password();	
	
	$.ajax({
		url: url,
		type: 'POST',
		data: {
			usr_phn_nmb: phoneNumber,
			usr_pwd: password
		},
		success: function(data) {
			json = JSON.parse(data);
			if (json == null) {
			} else if (!json.success) {
				tips(json.reason);
			} else {
				console.log(json);
				// register / login succeed
				_is_login = true;
				
				_user_name = json.data.usr_name;
				_user_id = json.data.usr_id;
				var user_avatar = json.data.usr_avtr;
				_my_playlists  = json.data.plylsts;
				
				$('.header--user--welcome').html(_user_name);
				$('.user-name').val(_user_name);
				$('.header--user--welcome').removeClass('hidden');
				$('.header--user--login-register').addClass('hidden');
				$('.user-avatar').attr('src', user_avatar);
				
				for (index in _my_playlists) {
					var playlist = _my_playlists[index];
					add_playlist(playlist);
				}
				
				// hide login
				$('.login').addClass('hidden');
				$('.black-background').addClass('hidden');	
				$('.register-option').addClass('login-register-option__selected');
				$('.login-option').removeClass('login-register-option__selected');
				$('.login-phone-number').val('');
				$('.login-password').val('');
				$('.login-register-button').removeClass('login-register-button__login');
				$('.login-register-button').addClass('login-register-button__register');
			}
		}
	});
}

function get_MD5_password() {
	var phoneNumber = $(".login-phone-number").val();
	var password = $(".login-password").val();
	
	// append phone number as salt value
	password += phoneNumber;
	
	// hash password
	password = md5(password);
	
	return password;
}

function validate_phone_number_and_password() {
	var phoneNumber = $(".login-phone-number").val();
	if (!is_phone_number_valid(phoneNumber)) {
		tips("invalid phone number!");
		return false;
	}
	var password = $(".login-password").val();
	if (password == null || !is_password_valid(password)) {
		tips("password must be at least 6 characters!");
		return false;
	}
	return true;
}

function is_phone_number_valid(phoneNumber) {
	return is_string_match(phoneNumber, "^1[3|4|5|7|8][0-9]{9}$");
}

function is_password_valid(password) {
	return is_string_match(password, "^[0-z]{6,}$")
}

function is_string_match(string, patternStr) {
	var pattern = new RegExp(patternStr);
	var match = string.match(pattern);
	if (match == null) {
		return false;
	} else {
		return true;
	}
}

function play() {
	var player = document.getElementById('audio');
	if ($('.play-button').hasClass('play-button__play')) {
		$('.play-button').removeClass('play-button__play');
		$('.play-button').addClass('play-button__pause');
		player.play();
		_is_playing = true;
	} else {
		$('.play-button').addClass('play-button__play');
		$('.play-button').removeClass('play-button__pause');
		player.pause();
		_is_playing = false;
	}
}

function track_progress() {
	if (!_is_playing || _music_duration == 0) {
		return;
	}
	var player = document.getElementById('audio');
	var current_percentage = (player.currentTime / _music_duration) * 100;
	var current_position = (current_percentage * 4) + 8;
	$('.progress-button').css("left", current_position+'px');
	$('.progress-bar_trace').css("width", current_position - 8 + 'px');
	 
}

function edit_user_name() {
	if ($('.user-name').hasClass('user-name__editing')) {
		$(this).html('编辑');
		$('.user-name').removeClass('user-name__editing');
		$('.user-name').attr('disabled', 'disabled');
		$('.user-name--save').addClass('hidden');
		$('.user-name').val(_user_name);
	} else {
		$(this).html('取消');
		$('.user-name').addClass('user-name__editing');
		$('.user-name').removeAttr('disabled');
		$('.user-name--save').removeClass('hidden');
	}
}

function save_user_name() {
	var user_name = $('.user-name').val();
	$('.user-name--edit').html('编辑');
	$('.user-name').removeClass('user-name__editing');
	$('.user-name').attr('disabled', 'disabled');
	$('.user-name--save').addClass('hidden');
	$('.user-name').val(_user_name);
	
	$.ajax({
		url: 'user/edit/info',
		type: 'POST',
		data: {
			usr_id: _user_id,
			usr_name: user_name
		},
		success: function(data) {
			json = JSON.parse(data);
			if (json.success) {
				_user_name = user_name;
				$('.header--user--welcome').html(_user_name);
				$('.user-name').val(_user_name);
			} else {
				tips(json.reason);
			}
		}
	});
}

function select_file() {
    $('#file-selector').trigger('click');
}

function file_did_select() {
    var file = document.getElementById('file-selector').files[0];
    if (file != null) {
        if (file.size > MAX_FILE_SIZE) {
            alert('文件太大！（ 不应超过1MB ）');
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
    upload_avatar(base64result);
}

function upload_avatar(file_data) {	
    $.ajax({
        url: 'user/edit/avatar',
        type: 'POST',
        data: {
        		usr_id: _user_id,
        		usr_avtr: "\""+file_data+"\""
        },
        success: function(result) {
            json = JSON.parse(result);
            if (json != null && json.success) {
                $('.user-avatar').attr('src', 'data:image/png;base64,'+file_data);
            } else if (json != null) {
                error_message = json.reason | 'Internet Error!';
                tips(error_message);
            }
        }
    });
}

function create_playlist() {
	$.ajax({
		url: 'playlist/create',
		type: 'POST',
		data: {
			usr_id: _user_id
		},
		success: function(data) {
			var json = JSON.parse(data);
			if (json != null) {
				if (json.success) {
					add_playlist(json.data);
				} else {
					tips(json.reason);
				}
			}
		}
	});
}

function edit_playlist() {
	
}

function delete_playlist() {
	
}

function add_playlist(playlist) {
	if (playlist == null) {
		return;
	}
	$('.user-playlist').append('<li class="user-playlist--item" \
			id='+playlist.plylst_id+' onmouseover="playlist_item_mouseover(\''+playlist.plylst_id+'\')"">\
			<input class="user-playlist--item--title" value="'+playlist.plylst_name+'" type="text" disabled="disabled">\
			<div class="user-playlist--action hidden user-playlist--delete">删除歌单</div>\
			<div class="user-playlist--action hidden user-playlist--edit">编辑歌单</div></li>');
}

function playlist_item_mouseover(id) {
	_current_playlist = id;
	$('#'+id).find('.user-playlist--action').removeClass('hidden');
}

function playlist_mouseout() {
	$(".user-playlist--delete").addClass('hidden');
	$(".user-playlist--edit").addClass('hidden');
}

function search_option() {
	if (!$(this).hasClass('search-bar--option__selected')) {
		$('.search-bar--option__selected').removeClass('search-bar--option__selected');
		$(this).addClass('search-bar--option__selected');
		if ($(this).hasClass('search-bar--option--name')) {
			_current_search_option = SEARCH_OPTION.MUSIC_NAME;
		} else if ($(this).hasClass('search-bar--option--singer')) {
			_current_search_option = SEARCH_OPTION.SINGER_NAME;
		} else {
			_current_search_option = SEARCH_OPTION.ALBUM_NAME;
		}
	}
}

function search() {
	var keyword = $('.search-bar--field').val();
	if (keyword == null || keyword.length == 0) {
		return;
	}
	
	var url = 'music/query/';
	var param = {};
	switch(_current_search_option) {
	case SEARCH_OPTION.MUSIC_NAME:
		url += 'keyword';
		param.kywd = keyword;
		break;
	case SEARCH_OPTION.SINGER_NAME:
		url += 'singer';
		param.sngr_name = keyword;
		break;
	case SEARCH_OPTION.ALBUM_NAME:
		url += 'album';
		param.albm_name = keyword;
		break;
	}
	
	param.page = 0;
	
	$.ajax({
		url: url,
		type: 'POST',
		data: param,
		success: function(data) {
			var json = JSON.parse(data);
			if (json == null) {
				
			} else {
				if (json.success) {
					$('.search-result-list').html('');
					$('.search-view').removeClass('hidden');
					for (var i = 0; i < json.data.length; i++) {
						add_search_music(json.data[i]);
					}
				} else {
					tips(json.reason);
				}
			}
		}
	});
}

function add_search_music(music) {
	if (music == null) {
		return;
	}
	var li_color = _should_search_result_f7 ? 'f7':'white';
	$('.search-result-list').append('<li class="search-result-container" onclick=play_music("'+music.msc_path+'",'+music.msc_lnth+')>\
			<div class="search-result background-'+li_color+'">\
			<span class="search-result--name">'+music.msc_name+'</span>\
			<span class="search-result--singer">'+music.msc_sngr+'</span>\
			<span class="search-result--album">'+music.msc_albm+'</span>\
			<span class="search-result--length">'+music.msc_lnth+'</span>\
			<span class="search-result--hot">'+music.msc_hot+'</span>\
			<div class="clear"></div></div></li>');
	_should_search_result_f7 = !_should_search_result_f7;
}

function play_music(path, duration) {
	$('#audio').attr('src',path);
	_music_duration = parseInt(duration);
	play();
}

function roll_music_title() {
	var width = $('.play-music-title').width();
	if (width < 100) {
		return;
	}
	var margin_left = ($('.play-music-title').css('margin-left').substring(0,$('.play-music-title').css('margin-left').length-2));
	if (margin_left < -1 * width) {
		margin_left = 100;
	} else {
		margin_left -= _ROLLING_RATE;
	}
	
	$('.play-music-title').css('margin-left', margin_left+'px');
}

function tips(msg) {
	alert(msg);
}