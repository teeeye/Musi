var Header = React.createClass({
	render: function() {
		return <div class="header">foo</div>;
	}
});


$(document).ready(function(){
	ReactDOM.render(<Header />, $('.root'));
});