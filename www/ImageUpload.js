cordova.define("cordova-plugin-uploadimage.ImageUpload", function(require, exports, module) {
function ImageUpload() {
}

ImageUpload.prototype.uploadImage = function (options, successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "ImageUpload", "uploadImage", [options]);
};

ImageUpload.install = function () {
  if (!window.plugins) {
    window.plugins = {};
  }

  window.plugins.uploadimage = new ImageUpload();

  return window.plugins.uploadimage;
};

cordova.addConstructor(ImageUpload.install);

});
