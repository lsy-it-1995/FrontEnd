GIT
# General command
* git clone <url>
	* download the content from the url
* ls (list)
* cd (change directory)
* touch filename (create)
* git add <filename>
	* doing * means every files
* git commit -m "message" (comment for each commit)
* git commit -am "message"
	* add and commit with message
* git status 
	* telling you what are the commits in the history
* git push
	* push to the github
* git pull
	* once you have the repository on computer
	* pull the respository from github to local
* git log
	* describe for each commit
* git reset
	* reset to other commits
		* git reset --hard <commit>
	* reset to current respoitory
		* git reset --hard origin/master
		
# Merge conflict
You made the change
```
<<<<<
code ...
===== 
```
someone else made the change
```
=====
code ...
>>>>>
```
 
