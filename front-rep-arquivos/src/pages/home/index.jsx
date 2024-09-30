import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './styles.css';

const DirectoryList = () => {
    const [directories, setDirectories] = useState([]);

    useEffect(() => {
        const fetchDirectories = async () => {
            const response = await axios.get('http://localhost:8080/api/diretorios');
            setDirectories(response.data);
        };
        fetchDirectories();
    }, []);

    const renderDirectories = (dirs) => {
        return dirs.map((dir) => (
            <React.Fragment key={dir.id}>
                <tr>
                    <td>{dir.nome}</td>
                    <td>{dir.subDirectories.length}</td>
                    <td></td> {/* Coluna de arquivos vazia para diretórios */}
                </tr>
                {dir.subDirectories && dir.subDirectories.length > 0 && renderDirectories(dir.subDirectories)}
                {dir.files && dir.files.length > 0 && dir.files.map((file) => (
                    <tr key={file.id}>
                        <td style={{ paddingLeft: '20px' }}>Arquivo: {file.nome}</td>
                        <td></td>
                        <td>{file.tamanho}</td> {/* Tamanho do arquivo na coluna de arquivos */}
                    </tr>
                ))}
            </React.Fragment>
        ));
    };

    return (
        <div className="container">
            <h2>Lista de Diretórios</h2>
            <table>
                <thead>
                    <tr>
                        <th>Diretório / Nome do arquivo</th>
                        <th>Qnt Subdiretórios</th>
                        <th>Tamanho arquivos</th>
                    </tr>
                </thead>
                <tbody>
                    {directories.length > 0 ? (
                        renderDirectories(directories)
                    ) : (
                        <tr>
                            <td colSpan="3">Nenhum diretório encontrado</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
};

export default DirectoryList;
