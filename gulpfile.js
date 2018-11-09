var gulp = require('gulp');
var less = require('less');
var through = require('through2');
var watch = require('gulp-watch');
var plumber = require('gulp-plumber');
var sourcemaps = require('gulp-sourcemaps');
var postcss = require('gulp-postcss');
var cssvariables = require('postcss-css-variables');
var postcssclean = require('postcss-clean');
var postcssdiscarddublicates = require('postcss-discard-duplicates');
var cssimport = require('postcss-import');
var concat = require('gulp-concat');
var minify = require('gulp-minify');
var runSequence = require('run-sequence');
var sass = require('gulp-sass');
var shell = require('gulp-shell');

gulp.task('postcss', function() {
    return gulp.src(['web/styles-dev/*.css'])
        .pipe(postcss([cssimport,cssvariables({preserve:true}),postcssdiscarddublicates,postcssclean]))
        .pipe(gulp.dest('web/styles'))
});

gulp.task('copy_original_styles', function() {
    return gulp.src(['web/styles-dev/*.css','web/styles-dev/*.less','web/styles-dev/*.scss','web/styles-dev/*.css.map'])
        .pipe(gulp.dest('../dps-prototype/styles'))
});

gulp.task('process_css', function(cb){
    runSequence(['postcss','copy_original_styles'],function() {
        cb();
    })
});

gulp.task('watch_copy_original_styles', function() {
    return watch(['web/styles-dev/*.css','web/styles-dev/*.less','web/styles-dev/*.scss','web/styles-dev/*.css.map'])
        .on("data",function(file) {
            var date = new Date();
            console.log('watch_copy_original_styles',date.toLocaleString(),file.history[0].substr(file.cwd.length));
        })
        .pipe(gulp.dest('../dps-prototype/styles'));
});

gulp.task('watch_copy_modified_files', function() {
    return watch(['web/**/*.jsp','web/**/*.css','web/**/*.js'])
        .on("data",function(file) {
            var date = new Date();
            console.log('watch_copy_modified_files',date.toLocaleString(),file.history[0].substr(file.cwd.length));
        })
        .pipe(gulp.dest("target/dps.dpswebsite"));
});

/*
gulp.task('copy_js', function () {
    gulp.src('node_modules/requirejs/require.js')
        .pipe(minify({
            ext:{
                src:'.js',
                min:'.min.js'
            },
        }))
        .pipe(gulp.dest('web/scripts'));
});
*/

gulp.task('watch_scss', function() {
    return watch(['web/styles-dev/*.scss'])
        .on("data",function(file) {
            var date = new Date();
            console.log('watch_scss',date.toLocaleString(),file.history[0].substr(file.cwd.length));
        })
        .pipe(sourcemaps.init())
        .pipe(sass())
        .pipe(sourcemaps.write("."))
        .pipe(gulp.dest("web/styles-dev"));
});

