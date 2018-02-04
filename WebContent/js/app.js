var _current_banner_index = 0;
var _banner_count = 3;
var _is_dragging = false;
var _current_progress = 0;

$(document).ready(function(){
    setInterval(slide_banner, 3000);
    $('.progress-bar_container').mousedown(function(){
        _is_dragging = true;
    });
    
    $('.progress-bar_container').mousemove(drag_progress);
    
    $(document).mouseup(relocate_progress);
    
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