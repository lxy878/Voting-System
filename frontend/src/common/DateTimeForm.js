export function showDateTime(data){
    const date = new Date(data)
    return `at ${date.toLocaleTimeString()} on ${date.toLocaleDateString('en-us')}`
}