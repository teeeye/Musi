var _current_banner_index = 0;
var _banner_count = 3;
var _is_dragging = false;
var _current_progress = 0;
var _is_playing = false;
var _music_duration = 0;
var _is_login = false;

$(document).ready(function(){
	
    setInterval(slide_banner, 3000);
    
    $('.progress-bar_container').mousedown(function(){
        _is_dragging = true;
    });
    
    $('.progress-bar_container').mousemove(drag_progress);
    
    $('.progress-bar_container').mouseup(relocate_progress);
    
    $('.header--user--login-register').click(show_login);
    
    $('.login-cancel').click(show_login);
    
    $('.user-avatar-container').mouseover(function(){
    		$('.user-avatar-edit').addClass('user-avatar-edit__shown');
    });
    
    $('.user-avatar-container').mouseout(function(){
		$('.user-avatar-edit').removeClass('user-avatar-edit__shown');
    });
    
	$('.header--nav--item').click(header_nav);
	
	$('.header--logo').click(header_nav);
	
	$('.login-register-option').click(login_register_option);
	
	$('.login-register-button').click(login_register);
	
	$('.play-button').click(play);
	
	setInterval(track_progress, 500);
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
		currentView.addClass('current-view');
		currentView.removeClass('hidden');
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
	
}

function play() {
	var player = document.getElementById('audio');
	if ($(this).hasClass('play-button__play')) {
		$(this).removeClass('play-button__play');
		$(this).addClass('play-button__pause');
		player.play();
		_music_duration = player.duration;
		_is_playing = true;
	} else {
		$(this).addClass('play-button__play');
		$(this).removeClass('play-button__pause');
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

function tips(msg) {
	alert(msg);
}