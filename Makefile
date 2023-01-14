.PHONY:

all: git

git:
	git add .
	git commit -m "$m"
	git push

