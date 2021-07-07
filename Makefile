dev:
	docker-compose -f docker-compose.dev.yml up --remove-orphans --force-recreate

test:
	docker-compose -f docker-compose.test.yml up --build --abort-on-container-exit --exit-code-from market-api --force-recreate