'use strict';

const gulp = require('gulp');
const liferayThemeTasks = require('liferay-theme-tasks');
const Promise = require('bluebird')
const path = require('path')
const fs = require('fs')
const MsTranslator = require('mstranslator')

liferayThemeTasks.registerTasks({
	gulp: gulp,
	hookFn: function(gulp) {
		gulp.hook('before:deploy', function(done) {
			gulp.start('add-text-to-language-properties-file')
			done()
		}),
		gulp.hook('after:deploy', function(done) {
			process.exit()
		})
	},
})

gulp.task('add-text-to-language-properties-file', function() {
	
	const dir = path.resolve(__dirname, './src/WEB-INF/src/content/Language')
	const ext = '.properties'
	const srcFile = dir + ext
	
	const countries = ['ar','bg','ca','cs','da','de','el', 'en', 'es','et','fa','fi','fr','hi_IN','hr','hu','it','ja','ko','lt','nl','pl','pt_PT','ro','ru','sk','sl','sr_RS_latin','sr_RS','sv','th','tr','uk','vi','zh_CN','zh_TW']

	const contents = fs.readFileSync(srcFile, 'utf8')
	const propertiesArr = contents.split('\r\n')		
	const substringArr = []

	propertiesArr.forEach((string) => {
		substringArr.push(string.match(/=(.+)/)[1])
	})

	const key = '39c6ec34dc684e9cae8c6be242041819'

	const client = new MsTranslator({ api_key: key }, true)
	
	const params = {
		texts: substringArr,
		from: 'en',
		to: 'es'
	}

	const fixNamesForMicrosoftApi = (country) => {
		let code = ''

		switch(country) {
			case 'hi_IN':
				code = 'hi'
				break
			case 'pt_PT':
				code = 'pt'
				break
			case 'sr_RS_latin':
				code = 'sr-Latn'
				break
			case 'sr_RS':
				code = 'sr-Cyrl'
				break
			case 'zh_CN':
				code = 'zh-CHS'
				break
			case 'zh_TW':
				code = 'zh-CHT'
				break 
			default:
				code = country
		}

		return code
	}

	client.initialize_token(function(err, keys) {
		
		countries.forEach((country, i) => {

			params.to = fixNamesForMicrosoftApi(country)

			client.translateArray(params, function(err, data) { // API Call

				const translated = data.map((translation, i) => {
					const string = propertiesArr[i]
					return string.slice(0, string.indexOf('=') + 1) + translation.TranslatedText
				})

				const destFile = dir + '_' + country + ext

				fs.writeFileSync(destFile, translated.join('\r\n'))

			})

		})

	})
	
})