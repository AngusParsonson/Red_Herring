(function(Backbone,_,ocBackbone,webshop,jQ){'use strict';var NO_SECTION='NO_SECTION';Backbone.Webshop=Backbone.Webshop||{};Backbone.Webshop.gtm=Backbone.Webshop.gtm||{};Backbone.Webshop.gtm.PromotionImpressionsTracker=Backbone.Marionette.Object.extend({initialize:function(){if(webshop.common.jsVariables.get('gaTrackPromos')){this.processedItems=[];this.pageType=webshop.common.jsVariables.get('pageType');this.eventSender=new ocBackbone.gtm.PromotionImpressionEventSender();this.registerListenerForInfiniteScrolling();this.sendPromotionImpressionsData();}},sendPromotionImpressionsData:function(container,pageType){var promotions=this.detectPromotions(container instanceof jQ.Event?undefined:container);this.sendImpressionsData(this.eventSender,promotions,pageType);},detectPromotions:function(container){var promotions=jQ("[data-element-type='promotion']",container);var promotionsFiltered=this.filterPromotionsThatShouldNotBeTracked(promotions);return this.groupPromotionsByHeader(promotionsFiltered,container);},filterPromotionsThatShouldNotBeTracked:function(promotions){return _.filter(promotions,function(promotion){return!jQ(promotion).is(':hidden');});},groupPromotionsByHeader:function(promotions,container){var groupedPromotions={};_.each(promotions,function(promotion){var position=promotion.getAttribute('data-promotion-position');if(!jQ.isNumeric(position)){position+=promotion.getAttribute('data-promotion-id');}
if(this.processedItems.indexOf(position)===-1||container){var promoImpression={id:promotion.getAttribute('data-promotion-id'),name:promotion.getAttribute('data-promotion-name'),creative:promotion.getAttribute('data-promotion-creative')};var extractedHeader=promotion.getAttribute('data-promotion-section');if(!container){if(!extractedHeader&&jQ.isNumeric(position)){extractedHeader=this.extractHeader(position);addPromoImpressionPosition(promoImpression,position);}else{promoImpression.position=promotion.getAttribute('data-promotion-position');}}
addToHeaderGroup(groupedPromotions,extractedHeader,promoImpression);this.processedItems.push(position);}},this);return groupedPromotions;},sendImpressionsData:function(eventSender,groupedPromos,pageType){_.each(groupedPromos,function(productsInSingleSection,sectionTitle){if(productsInSingleSection.length>0){var impressions={promotions:productsInSingleSection,pageType:pageType?pageType:this.pageType};if(sectionTitle!=NO_SECTION){impressions.sectionTitle=sectionTitle;impressions.productSectionDimension=sectionTitle;}else{impressions.sectionTitle=pageType?pageType:this.pageType;}
eventSender.sendImpressions(impressions);}},this);},registerListenerForInfiniteScrolling:function(){jQ(document.body).on('webshop:gtm:newProductsLoaded',jQ.proxy(this.sendPromotionImpressionsData,this));},extractHeader:function(sku){var extractedHeader=jQ("#fopMetaData-"+sku);if(extractedHeader&&extractedHeader.attr('data-collection-name')){return extractedHeader.attr('data-collection-name');}else{extractedHeader=this.extractHeaderForProduct(sku);return extractedHeader.isClusterHeader?this.pageType:extractedHeader.value;}},extractHeaderForProduct:function(sku){var foundHeader={};var foundClusterHeader=this.extractHeaderBySelector("#fopMetaDataClusterHeader-"+sku);if(!_.isUndefined(foundClusterHeader)){foundHeader.value=foundClusterHeader;foundHeader.isClusterHeader=true;}else{foundHeader.value=this.extractHeaderBySelector("#fopMetaDataHeader-"+sku);if(!foundHeader.value){foundHeader.value=this.extractPrevHeader("fopMetaDataHeader-",sku);}}
return foundHeader;},extractPrevHeader:function(fopHeaderId,sku){var fopHeader;var prevs=jQ("#productDetails-"+sku).prevAll("li");var fopHeaderElement=_.find(prevs,function(prev){var header=jQ("[id^='"+fopHeaderId+"']",prev).first().html();if(header){return header;}});if(!_.isUndefined(fopHeaderElement)){fopHeader=this.extractHeaderBySelector("[id^='fopMetaDataHeader-']",fopHeaderElement);}else{fopHeader=this.extractHeaderBySelector("h2 span",jQ("#productDetails-"+sku).prevAll("li").last());}
return fopHeader;},extractHeaderBySelector:function(selector,context){var fopHeader=jQ(selector,context).first().html();if(!_.isUndefined(fopHeader)){fopHeader=fopHeader.trim().replace(/&nbsp;/g,'');}
return fopHeader;}});function addPromoImpressionPosition(promoImpression,sku){var extractedHeader=jQ("#fopMetaData-"+sku);var skuList=jQ("[data-collection-name]");var position=skuList.index(extractedHeader);if(position>=0){promoImpression.position=position;}}
function addToHeaderGroup(groupedProducts,header,promo){if(!header){header=NO_SECTION;}
if(_.isUndefined(groupedProducts[header])){groupedProducts[header]=[];}
groupedProducts[header].push(promo)}
jQ(document).ready(function(){webshop.gtm.PromotionImpressionsTracker=new Backbone.Webshop.gtm.PromotionImpressionsTracker();});})(Backbone,_,ocBackbone,webshop,jQ);(function(Backbone){'use strict';ocBackbone.gtm=ocBackbone.gtm||{};ocBackbone.gtm.PromotionImpressionEventSender=Backbone.Marionette.Object.extend({initialize:function(){webshop.gtm.registerEventSource(this);},sendImpressions:function(promotions){sendPromotionImpression.call(this,promotions);}});function sendPromotionImpression(promotionImpressions){this.trigger('gtm:promotionImpressions',promotionImpressions,webshop.common.jsVariables.get('maxImpressions'));}})(Backbone,webshop);(function(Backbone,jQ,ocBackbone,webshop){'use strict';ocBackbone.gtm=ocBackbone.gtm||{};ocBackbone.gtm.LoginEventSender=Backbone.Marionette.Object.extend({initialize:function(){webshop.gtm.registerEventSource(this);},sendLoginEvents:function(loginSource,loginType,client){this.trigger('gtm:login',loginSource,client);this.trigger('gtm:loginType',loginSource,loginType);}});jQ(document).ready(function(){var loginType=webshop.common.jsVariables.get('gaLoginType');if(loginType){var devices=navigator.userAgent.match(/(iPhone|iPad|Android)/);var client=devices?devices[0]:'Desktop';var eventSender=new ocBackbone.gtm.LoginEventSender();var action='';switch(loginType){case'FACEBOOK':action='Facebook';break;case'PAYPAL':action='Paypal';break;default:action='Normal';break;}
eventSender.sendLoginEvents('Webshop',action,client);}});})(Backbone,jQ,ocBackbone,webshop);(function(Backbone,_,ocBackbone,webshop){'use strict';Backbone.Webshop.catalogue.GTMPromotionClickTracker=Backbone.Marionette.Object.extend({initialize:function(){if(webshop.common.jsVariables.get('gaTrackPromos')){webshop.gtm.registerEventSource(this);Backbone.$(document.body).on('click',"[data-element-type='promotion']",this.sendPromotionClickEvent.bind(this));}},sendPromotionClickEvent:function(event){var promo=Backbone.$(event.currentTarget);if(!_.isEmpty(promo)){var position=getPromoPosition(promo);this.trigger('gtm:promotionClick',{promo:{id:promo.attr('data-promotion-id'),name:promo.attr('data-promotion-name'),creative:promo.attr('data-promotion-creative'),position:position},pageType:getPageType(),sectionTitle:promo.attr('data-promotion-section')});}}});function getPromoPosition(promo){var promoPosition=promo.attr('data-promotion-position');if(Backbone.$.isNumeric(promoPosition)){if(promo.parents("#bopPopup").length){return null;}else{var extractedHeader=Backbone.$("#fopMetaData-"+promoPosition);var skuList=Backbone.$("[data-collection-name]");var index=skuList.index(extractedHeader);return index>=0?index:null;}}else{return promoPosition;}}
function getPageType(){if(Backbone.Webshop.mixins.isCheckout()){return'Checkout walk';}
var pageType=webshop.common.jsVariables.get('pageType');if(_.isEmpty(pageType)){return'Other';}else{return pageType;}}
Backbone.$(function(){if(webshop.common.jsVariables.get('gaTrackPromos')){ocBackbone.gtm=ocBackbone.gtm||{};ocBackbone.gtm.gtmPromotionClickTracker=ocBackbone.gtm.gtmPromotionClickTracker||new Backbone.Webshop.catalogue.GTMPromotionClickTracker();}});})(Backbone,_,ocBackbone,webshop);