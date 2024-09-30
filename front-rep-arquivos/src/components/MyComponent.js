import React from 'react';


const MyComponent = ({ directories }) => {
  return (
    <div>
      <h1>Lista de Diretórios</h1>
      {directories.length > 0 ? (
        <ul>
          {directories.map((dir) => (
            <li key={dir.id}>
              {dir.nome} - {dir.files.length} arquivos
            </li>
          ))}
        </ul>
      ) : (
        <p>Nenhum diretório encontrado</p>
      )}
    </div>
  );
};

export default MyComponent;