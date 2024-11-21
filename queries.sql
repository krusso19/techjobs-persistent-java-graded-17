--Part 1
-- id (int) Primary Key
-- name (varchar)
-- employer (varchar)
-- skills (varchar)

--Part 2
SELECT name
FROM employer
WHERE location = "St. Louis City";

--Part 3
DROP
TABLE job;

--Part 4
SELECT *
FROM skill
LEFT (JOIN job_skills ON (skill.id = job_skills.skills_id | job_skills.skills_id = skill.id)
WHERE job_skills.job_id IS NOT NULL)
ORDER BY name ASC;