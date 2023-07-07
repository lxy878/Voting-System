export function showDateTime(data){
    const date = new Date(data)
    return `AT ${date.toLocaleTimeString()} ON ${date.toLocaleDateString('en-us')}`
}