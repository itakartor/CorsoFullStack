--Esercizio solo query con telefoni
SELECT *
FROM Smartphone

SELECT Smartphone.nome, Smartphone.ram, Smartphone.dimensioni, Smartphone.cpu
FROM Smartphone

SELECT Smartphone.nome 
FROM Smartphone
WHERE Smartphone.display_resolution LIKE '1080x1920'

SELECT Smartphone.nome 
FROM Smartphone
WHERE Smartphone.display_resolution Like'1080x1920' AND Smartphone.Ram like '3Gb'

SELECT *
FROM Smartphone
where Smartphone.nome LIKE '%Galaxy%'

SELECT *
FROM Smartphone
where Smartphone.nome LIKE '%dual-core%'

SELECT *
FROM Smartphone
where Smartphone.peso >150

SELECT *
FROM Smartphone
where Smartphone.cpu LIKE '%Quad core%'
ORDER BY Smartphone.display_ppi

SELECT Smartphone.nome,MarcheTelefoni.Brand
FROM Smartphone
	JOIN MarcheTelefoni ON MarcheTelefoni.IdMarcaTelefono = Smartphone.IdMarcaTelefono

SELECT Smartphone.nome,Os.Descrizione
FROM Smartphone
	JOIN Os ON Os.IdOs = Smartphone.IdOs
ORDER BY Os.Descrizione

SELECT *
FROM Smartphone
	JOIN Os ON Os.IdOs = Smartphone.IdOs
	WHERE Os.Descrizione LIKE 'Android'
ORDER BY Smartphone.peso

SELECT *
FROM Smartphone
	JOIN MarcheTelefoni ON MarcheTelefoni.IdMarcaTelefono = Smartphone.IdMarcaTelefono
	WHERE MarcheTelefoni.Brand LIKE 'Samsung'
ORDER BY Smartphone.peso

SELECT Smartphone.nome, MarcheTelefoni.Brand, Os.Descrizione
FROM Smartphone
	JOIN MarcheTelefoni ON MarcheTelefoni.IdMarcaTelefono = Smartphone.IdMarcaTelefono
	JOIN Os ON Os.IdOs = Smartphone.IdOs
	
SELECT COUNT(*) AS NumeroTelefono2Gb
FROM Smartphone
WHERE Smartphone.ram LIKE '2Gb'

SELECT COUNT(*) AS NumeroTelefonoWindowsPhone8
FROM Smartphone
	JOIN Os ON Os.IdOs = Smartphone.IdOs 
WHERE Os.Descrizione LIKE 'Windows Phone 8'

SELECT COUNT(*) AS NumeroTelefoni, Os.Descrizione
FROM Smartphone
	JOIN Os ON Os.IdOs = Smartphone.IdOs
GROUP BY os.Descrizione

SELECT * 
FROM Smartphone
WHERE Smartphone.peso IN (
	SELECT MIN(Smartphone.peso)
	FROM Smartphone
)

SELECT * 
FROM Smartphone
WHERE Smartphone.display_ppi IN (
	SELECT MAX(Smartphone.display_ppi)
	FROM Smartphone
)

SELECT convert(DECIMAL(5,2), AVG(CONVERT(DECIMAL(5,2),Smartphone.display_size))) AS MediaDimensioneSchermo
FROM Smartphone