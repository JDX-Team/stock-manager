'use strict';

var module = angular.module('app.common.directive');

module.directive(
				'loadmenu',['RecursionHelper',
				function(RecursionHelper) {
					return {
						restrict : "E",
						scope : {
							family : '='
						},
						template : '<ul >'
								+ '<li ng-repeat="child in family" >'
								+ '<a class="enlace" ui-sref="{{child.url}}" ng-mouseover="child.selected=true" ng-mouseleave="child.selected=false">'
								+ '<span class="{{child.class}}"></span>'
								+ '{{child.name}}</a>'
								+ '<div class="submenu" ng-show="child.selected" ng-mouseover="child.selected=true" ng-mouseleave="child.selected=false">'
								+ '<loadmenu family="child.children" > </loadmenu>'
								+ '</div>' + '</li>' + '</ul>',
						compile : function(element) {
							return RecursionHelper
									.compile(
											element,
											function(scope, iElement, iAttrs,
													controller, transcludeFn) {
												// Define your normal link
												// function here.
												// Alternative: instead of
												// passing a function,
												// you can also pass an object
												// with
												// a 'pre'- and 'post'-link
												// function.
												if (iElement[0]
														.querySelector('.enlace') != null)
													$compile(
															iElement[0]
																	.querySelector('.enlace'))
															(scope);
											});
						}
					};
				}]);